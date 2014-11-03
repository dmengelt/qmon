package ch.filecloud.queuemonitor.client.hornetq.queue;

import ch.filecloud.queuemonitor.client.hornetq.AbstractHornetQClient;
import ch.filecloud.queuemonitor.client.hornetq.exception.QmonConnectionException;
import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.JMSQueueControl;
import org.springframework.stereotype.Service;

import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domi on 10/20/14.
 */
@Service
public class HornetQQueueClient extends AbstractHornetQClient {

    private static final String JMS_QUEUE_PREFIX = "jms.queue.";

    public QueueInfo getQueue(String queueName) {
        return createQueueInfo(queueName);
    }

    public List<QueueInfo> getQueues() {
        List<QueueInfo> queueInfos = new ArrayList<QueueInfo>();
        for (String queue : getJMSServerControl().getQueueNames()) {
            queueInfos.add(createQueueInfo(queue.replace(JMS_QUEUE_PREFIX,"")));
        }
        return queueInfos;
    }

    public void clearMessages(String queueName) {
        try {
            JMSQueueControl jmsQueueControl = getJMSQueueControl(queueName);
            jmsQueueControl.removeMessages("");
        } catch (Exception e) {
            throw new QmonConnectionException("An error occurred while trying to clear the messages for queue " + queueName, e);
        }
    }

    private JMSQueueControl getJMSQueueControl(String queueName) {
        try {
            ObjectName objectName = ObjectNameBuilder.DEFAULT.getJMSQueueObjectName(queueName);
            return MBeanServerInvocationHandler.newProxyInstance(jmxConnectionClient.get(), objectName, JMSQueueControl.class, false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object name " + queueName + " not found!", e);
        }
    }

    private QueueInfo createQueueInfo(String queueName) {
        try {
            JMSQueueControl jmsQueueControl = getJMSQueueControl(queueName);
            return new QueueInfo(queueName,
                    jmsQueueControl.getMessageCount(),
                    jmsQueueControl.getMessagesAdded(),
                    jmsQueueControl.getConsumerCount());
        } catch (Exception e) {
            throw new QmonConnectionException("Error occurred during lookup of queues", e);
        }
    }
}

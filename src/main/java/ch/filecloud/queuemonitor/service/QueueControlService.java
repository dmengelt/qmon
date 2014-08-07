package ch.filecloud.queuemonitor.service;

import ch.filecloud.queuemonitor.domain.QueueInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.JMSQueueControl;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by domi on 7/30/14.
 */
@Component
public class QueueControlService extends ControlService {

    private static Log LOGGER = LogFactory.getLog(QueueControlService.class);
    private static final String JMS_QUEUE_PREFIX = "jms.queue.";

    public List<QueueInfo> getQueues() {
        List<QueueInfo> queueInfos = new ArrayList<QueueInfo>();
            for (String queue : getJMSServerControl().getQueueNames()) {
                LOGGER.info("Creating queue info object for queue with name " + queue);
                queueInfos.add(createQueueInfo(queue.replace(JMS_QUEUE_PREFIX,"")));
            }
        return queueInfos;
    }

    public QueueInfo getQueue(String queueName) {
        return createQueueInfo(queueName);
    }

    private JMSQueueControl getJMSQueueControl(String queueName) {
        try {
            ObjectName objectName = ObjectNameBuilder.DEFAULT.getJMSQueueObjectName(queueName);
            return MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnectionFactoryBean.getObject(), objectName, JMSQueueControl.class, false);
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
            LOGGER.error("Error occurred during lookup of queues", e);
            // TODO - throw custom exception and add global exception handler (spring aop or @ExceptionHandler)
            return null;
        }
    }

}

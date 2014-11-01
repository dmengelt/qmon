package ch.filecloud.queuemonitor.client.topic;

import ch.filecloud.queuemonitor.client.AbstractHornetQClient;
import ch.filecloud.queuemonitor.service.exception.QmonConnectionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.SubscriptionInfo;
import org.hornetq.api.jms.management.TopicControl;
import org.springframework.stereotype.Service;

import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**org
 * Created by domi on 10/19/14.
 */
@Service
public class HornetQTopicClient extends AbstractHornetQClient {

    private static Log LOGGER = LogFactory.getLog(HornetQTopicClient.class);

    public TopicInfo getTopic(String topicName) {
        return createTopicInfo(topicName);
    }

    public List<TopicInfo> getTopics() {
        List<String> topicNames = getTopicNames();
        List<TopicInfo> topicInfos = new ArrayList<TopicInfo>();

        for(String topicName : topicNames) {
            topicInfos.add(createTopicInfo(topicName));
        }

        return topicInfos;
    }

    public List<String> getTopicNames() {
        return Arrays.asList(getJMSServerControl().getTopicNames());
    }

    // TODO - introduce custom DTO in order to avoid hornetq specific import on service level
    public List<SubscriptionInfo> getSubscriptionInfo(String topicName) {
        try {
            // listDurableSubscriptionsAsJSON()
            return Arrays.asList(SubscriptionInfo.from(getTopicControl(topicName).listAllSubscriptionsAsJSON()));
        } catch (Exception e) {
            LOGGER.error("Error occurred while trying to get the subscriptions for topic " + topicName, e);
            throw new QmonConnectionException(e);
        }
    }

    public void dropDurableSubscription(String topicName, String clientID, String name) {
        try {
            getTopicControl(topicName).dropDurableSubscription(clientID, name);
        } catch (Exception e) {
            LOGGER.error("Error occurred while trying to delete the subscription " + name + " for topic " + topicName, e);
            throw new QmonConnectionException(e);
        }
    }

    private TopicInfo createTopicInfo(String topicName) {
        try {
            TopicControl topicControl = getTopicControl(topicName);
            return new TopicInfo(topicName,
                    topicControl.getMessageCount(),
                    topicControl.getMessagesAdded(),
                    topicControl.getSubscriptionCount(),
                    getSubscriptionInfo(topicName));
        } catch (Exception e) {
            throw new QmonConnectionException(e);
        }
    }

    private TopicControl getTopicControl(String topicName) {
        try {
            ObjectName objectName = ObjectNameBuilder.DEFAULT.getJMSTopicObjectName(topicName);
            return MBeanServerInvocationHandler.newProxyInstance(jmxConnectionClient.get(), objectName, TopicControl.class, false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object name not found!", e);
        }
    }

}

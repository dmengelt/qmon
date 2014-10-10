package ch.filecloud.queuemonitor.service.topic;

import ch.filecloud.queuemonitor.service.ControlService;
import ch.filecloud.queuemonitor.service.exception.QmonConnectionException;
import ch.filecloud.queuemonitor.service.exception.TopicSubscriptionNotDurableException;
import ch.filecloud.queuemonitor.service.exception.TopicSubscriptionNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.SubscriptionInfo;
import org.hornetq.api.jms.management.TopicControl;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import java.util.*;

/**
 * Created by domi on 8/3/14.
 */
@Component
public class TopicControlService extends ControlService {

    private static Log LOGGER = LogFactory.getLog(TopicControlService.class);

    public List<TopicInfo> getTopics() {
        List<TopicInfo> topicInfos = new ArrayList<TopicInfo>();

        for (String topic : getJMSServerControl().getTopicNames()) {
            topicInfos.add(createTopicInfo(topic));
        }

        return topicInfos;
    }

    public TopicInfo getTopic(String topicName) {
        return createTopicInfo(topicName);
    }

    public void dropDurableSubscription(String topicName, String subscriptionName) {
        TopicControl topicControl = getTopicControl(topicName);

        List<SubscriptionInfo> subscriptions = getSubscriptionInfo(topicName, topicControl);

        if (subscriptions == null || subscriptions.size() == 0) {
            throw new TopicSubscriptionNotFoundException();
        }
        
        for (SubscriptionInfo subscription : subscriptions) {
            if (subscription.getName().equals(subscriptionName)) {

                if(!subscription.isDurable()) {
                    throw new TopicSubscriptionNotDurableException();
                }

                try {
                    topicControl.dropDurableSubscription(subscription.getClientID(), subscription.getName());
                } catch (Exception e) {
                    LOGGER.error("Error occurred while trying to delete the subscription " + subscriptionName + " for topic " + topicName, e);
                    throw new QmonConnectionException(e);
                }
            }
        }

    }

    private TopicInfo createTopicInfo(String topicName) {
        try {
            TopicControl topicControl = getTopicControl(topicName);
            return new TopicInfo(topicName,
                    topicControl.getMessageCount(),
                    topicControl.getMessagesAdded(),
                    topicControl.getSubscriptionCount(),
                    getSubscriptionInfo(topicName, topicControl));
        } catch (Exception e) {
            throw new QmonConnectionException(e);
        }
    }

    private List<SubscriptionInfo> getSubscriptionInfo(String topicName, TopicControl topicControl) {
        try {
            // listDurableSubscriptionsAsJSON()
            return Arrays.asList(SubscriptionInfo.from(topicControl.listAllSubscriptionsAsJSON()));
        } catch (Exception e) {
            LOGGER.error("Error occurred while trying to get the subscriptions for topic " + topicName, e);
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

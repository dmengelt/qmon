package ch.filecloud.queuemonitor.service.topic;

import ch.filecloud.queuemonitor.client.topic.HornetQTopicClient;
import ch.filecloud.queuemonitor.client.topic.TopicInfo;
import ch.filecloud.queuemonitor.service.exception.TopicSubscriptionNotDurableException;
import ch.filecloud.queuemonitor.service.exception.TopicSubscriptionNotFoundException;
import org.hornetq.api.jms.management.SubscriptionInfo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by domi on 8/3/14.
 */
@Service
public class TopicControlService {

    @Inject
    private HornetQTopicClient hornetQManagementClient;

    public TopicInfo getTopic(String topicName) {
        return hornetQManagementClient.getTopic(topicName);
    }

    public List<TopicInfo> getTopics() {
        return hornetQManagementClient.getTopics();
    }

    public void dropDurableSubscription(String topicName, String subscriptionName) {
        List<SubscriptionInfo> subscriptions = hornetQManagementClient.getSubscriptionInfo(topicName);

        if (subscriptions == null || subscriptions.size() == 0) {
            throw new TopicSubscriptionNotFoundException();
        }

        for (SubscriptionInfo subscription : subscriptions) {
            if (subscription.getName().equals(subscriptionName)) {

                if (!subscription.isDurable()) {
                    throw new TopicSubscriptionNotDurableException();
                }

                hornetQManagementClient.dropDurableSubscription(topicName, subscription.getClientID(), subscription.getName());
            }
        }
    }
}

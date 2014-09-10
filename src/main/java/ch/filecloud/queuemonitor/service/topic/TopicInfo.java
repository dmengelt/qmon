package ch.filecloud.queuemonitor.service.topic;

import org.hornetq.api.jms.management.SubscriptionInfo;

import java.util.List;

/**
 * @author domi
 */
public final class TopicInfo {

    private final String name;
    private final long messageCount;
    private final long messagesAdded;
    private final int subscribtionCount;
    private List<SubscriptionInfo> subscriptionInfo;

    public TopicInfo(String topicName, long messageCount, long messagesAdded, int subscribtionCount, List<SubscriptionInfo> subscriptionInfo) {
        this.name = topicName;
        this.messageCount = messageCount;
        this.messagesAdded = messagesAdded;
        this.subscribtionCount = subscribtionCount;
        this.subscriptionInfo = subscriptionInfo;
    }

    public String getName() {
        return name;
    }

    public long getMessageCount() {
        return messageCount;
    }

    public long getMessagesAdded() {
        return messagesAdded;
    }

    public int getSubscribtionCount() {
        return subscribtionCount;
    }

    public List<SubscriptionInfo> getSubscriptionInfo() {
        return subscriptionInfo;
    }
}
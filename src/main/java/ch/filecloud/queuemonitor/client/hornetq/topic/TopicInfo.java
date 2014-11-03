package ch.filecloud.queuemonitor.client.hornetq.topic;

import org.hornetq.api.jms.management.SubscriptionInfo;

import java.util.List;

/**
 * @author domi
 */
public final class TopicInfo {

    private final String name;
    private final long messageCount;
    private final long messagesAdded;
    private final int subscriptionCount;
    private List<SubscriptionInfo> subscriptions;

    public TopicInfo(String topicName, long messageCount, long messagesAdded, int subscriptionCount, List<SubscriptionInfo> subscriptions) {
        this.name = topicName;
        this.messageCount = messageCount;
        this.messagesAdded = messagesAdded;
        this.subscriptionCount = subscriptionCount;
        this.subscriptions = subscriptions;
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

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public List<SubscriptionInfo> getSubscriptions() {
        return subscriptions;
    }
}
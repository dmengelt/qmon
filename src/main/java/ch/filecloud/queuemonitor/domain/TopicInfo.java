package ch.filecloud.queuemonitor.domain;

/**
 * Created by domi on 8/4/14.
 */
public final class TopicInfo {

    private final String name;
    private final long messageCount;
    private final long messagesAdded;
    private final int subscribtionCount;

    public TopicInfo(String topicName, long messageCount, long messagesAdded, int subscribtionCount) {
        this.name = topicName;
        this.messageCount = messageCount;
        this.messagesAdded = messagesAdded;
        this.subscribtionCount = subscribtionCount;
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
}
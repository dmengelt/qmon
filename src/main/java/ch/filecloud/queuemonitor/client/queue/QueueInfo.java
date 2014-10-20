package ch.filecloud.queuemonitor.client.queue;

/**
 * Created by domi on 8/4/14.
 */
public final class QueueInfo {

    private final String queueName;
    private final long messageCount;
    private final long messagesAdded;
    private final long consumerCount;

    public QueueInfo(String queueName, long messageCount, long messagesAddes, long consumerCount) {
        this.queueName = queueName;
        this.messageCount = messageCount;
        this.messagesAdded = messagesAddes;
        this.consumerCount = consumerCount;
    }

    public String getQueueName() {
        return queueName;
    }

    public long getMessageCount() {
        return messageCount;
    }

    public long getMessagesAdded() {
        return messagesAdded;
    }

    public long getConsumerCount() {
        return consumerCount;
    }
}
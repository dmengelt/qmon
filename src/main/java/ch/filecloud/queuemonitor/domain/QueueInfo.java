package ch.filecloud.queuemonitor.domain;

/**
 * Created by domi on 8/4/14.
 */
public final class QueueInfo {

    private final String queueName;
    private final long messageCount;
    private final long messagesAdded;

    public QueueInfo(String queueName, long messageCount, long messagesAddes) {
        this.queueName = queueName;
        this.messageCount = messageCount;
        this.messagesAdded = messagesAddes;
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
}
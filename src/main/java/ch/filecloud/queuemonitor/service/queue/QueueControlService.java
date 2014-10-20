package ch.filecloud.queuemonitor.service.queue;

import ch.filecloud.queuemonitor.client.queue.HornetQQueueClient;
import ch.filecloud.queuemonitor.client.queue.QueueInfo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by domi on 7/30/14.
 */
@Service
public class QueueControlService {

    @Inject
    private HornetQQueueClient hornetQQueueClient;

    public QueueInfo getQueue(String queueName) {
        return hornetQQueueClient.getQueue(queueName);
    }

    public List<QueueInfo> getQueues() {
        return hornetQQueueClient.getQueues();
    }

    public void clearMessages(String queueName) {
        hornetQQueueClient.clearMessages(queueName);
    }
}

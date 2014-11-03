package ch.filecloud.queuemonitor.web.api.queue;

import ch.filecloud.queuemonitor.client.hornetq.queue.QueueInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by domi on 8/5/14.
 */
public class QueueInfoResponseDTO {

    public static final String QUEUES = "queues";
    private List<QueueInfo> queueInfos;

    public QueueInfoResponseDTO(List<QueueInfo> queueInfos) {
        this.queueInfos = queueInfos;
    }

    @JsonProperty(QUEUES)
    public List<QueueInfo> getQueueInfos() {
        return queueInfos;
    }
}

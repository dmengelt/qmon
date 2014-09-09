package ch.filecloud.queuemonitor.web.api.queue;

import ch.filecloud.queuemonitor.service.queue.QueueInfo;
import ch.filecloud.queuemonitor.service.queue.QueueControlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by domi on 7/21/14.
 */
@Controller
@RequestMapping("/monitor/queues")
public class QueueMonitorController {

    @Inject
    private QueueControlService queueControlService;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueueInfoResponseDTO getQueues() {
        return new QueueInfoResponseDTO(queueControlService.getQueues());
    }

    @RequestMapping(value = "/{queueName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueueInfo getMessageCount(@PathVariable("queueName") String queueName) {
        return queueControlService.getQueue(queueName);
    }
}

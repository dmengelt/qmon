package ch.filecloud.queuemonitor.web.api.queue;

import ch.filecloud.queuemonitor.service.queue.QueueInfo;
import ch.filecloud.queuemonitor.service.queue.QueueControlService;
import ch.filecloud.queuemonitor.web.api.system.EnvironmentUpdateRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by domi on 7/21/14.
 */
@Controller
@RequestMapping("/monitor/queues")
public class QueueMonitorController {

    private static final String QUEUE_NAME = "queueName";

    @Inject
    private QueueControlService queueControlService;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueueInfoResponseDTO getQueues() {
        return new QueueInfoResponseDTO(queueControlService.getQueues());
    }

    @RequestMapping(value = "/{queueName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueueInfo getMessageCount(@PathVariable(QUEUE_NAME) String queueName) {
        return queueControlService.getQueue(queueName);
    }

    @RequestMapping(value = "/{queueName}/clear", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus( HttpStatus.OK )
    public void clearMessages(@PathVariable(QUEUE_NAME) String queueName) {
        queueControlService.clearMessages(queueName);
    }

}

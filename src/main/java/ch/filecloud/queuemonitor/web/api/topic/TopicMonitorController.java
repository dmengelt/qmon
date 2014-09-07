package ch.filecloud.queuemonitor.web.api.topic;

import ch.filecloud.queuemonitor.domain.TopicInfo;
import ch.filecloud.queuemonitor.service.TopicControlService;
import ch.filecloud.queuemonitor.service.exception.TopicSubscriptionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by domi on 8/2/14.
 */
@Controller
@RequestMapping("/monitor/topics")
public class TopicMonitorController {

    @Inject
    private TopicControlService topicControlService;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TopicInfoResponseDTO getTopics() {
        return new TopicInfoResponseDTO(topicControlService.getTopics());
    }

    @RequestMapping(value = "/{topicName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TopicInfo getTopic(@PathVariable("topicName") String topicName) {
        return topicControlService.getTopic(topicName);
    }

    @RequestMapping(value = "/{topicName}/{subscribtionName}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> dropDurableSubscription(@PathVariable("topicName") String topicName, @PathVariable("subscribtionName") String subscribtionName) {
        try {
            topicControlService.dropDurableSubscription(topicName, subscribtionName);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch (TopicSubscriptionNotFoundException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }


}

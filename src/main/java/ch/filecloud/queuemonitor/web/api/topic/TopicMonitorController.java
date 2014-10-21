package ch.filecloud.queuemonitor.web.api.topic;

import ch.filecloud.queuemonitor.client.topic.TopicInfo;
import ch.filecloud.queuemonitor.service.topic.TopicControlService;
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

    private static final String TOPIC_NAME = "topicName";
    private static final String SUBSCRIPTION_NAME = "subscriptionName";

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

    @RequestMapping(value = "/{topicName}/subscriptions/{subscriptionName:.+}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> dropDurableSubscription(@PathVariable(TOPIC_NAME) String topicName, @PathVariable(SUBSCRIPTION_NAME) String subscriptionName) {
        try {
            topicControlService.dropDurableSubscription(topicName, subscriptionName);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } catch (TopicSubscriptionNotFoundException e) {
            return new ResponseEntity<String>("Unable to find subscription '" + subscriptionName + "' for topic '" + topicName + "'",HttpStatus.NOT_FOUND);
        }
    }

}

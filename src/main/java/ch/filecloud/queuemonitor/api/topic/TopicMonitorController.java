package ch.filecloud.queuemonitor.api.topic;

import ch.filecloud.queuemonitor.domain.TopicInfo;
import ch.filecloud.queuemonitor.service.TopicControlService;
import org.hornetq.api.jms.management.SubscriptionInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by domi on 8/2/14.
 */
@Controller
@RequestMapping("/monitor/topics")
public class TopicMonitorController {

    @Inject
    private TopicControlService topicControlService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TopicInfoResponseDTO getTopics() {
        return new TopicInfoResponseDTO(topicControlService.getTopics());
    }

    @RequestMapping(value = "/{topicName}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public TopicInfo getTopic(@PathVariable("topicName") String topicName) {
        return topicControlService.getTopic(topicName);
    }


}

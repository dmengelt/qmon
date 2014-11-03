package ch.filecloud.queuemonitor.service.topic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by domi on 9/7/14.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Topic subscription not found")
public class TopicSubscriptionNotFoundException extends RuntimeException {
}

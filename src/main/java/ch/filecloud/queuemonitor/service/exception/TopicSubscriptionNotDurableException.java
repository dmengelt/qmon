package ch.filecloud.queuemonitor.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by domi on 9/7/14.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Topic subscription not of type durable!")
public class TopicSubscriptionNotDurableException extends RuntimeException {
}

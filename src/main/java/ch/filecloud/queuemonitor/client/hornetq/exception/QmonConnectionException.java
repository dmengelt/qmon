package ch.filecloud.queuemonitor.client.hornetq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by domi on 9/7/14.
 * TODO - find a better name for this exception
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unable to get monitoring information from HornetQ")
public class QmonConnectionException extends RuntimeException {

    public QmonConnectionException(Exception exception) {
        super(exception);
    }

    public QmonConnectionException(String message, Exception exception) {
        super(message, exception);
    }

}

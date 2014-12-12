package ch.filecloud.queuemonitor.common;

/**
 * Created by domi on 12/12/14.
 */
public class QmonConfigurationFailedException extends RuntimeException {
    public QmonConfigurationFailedException(String message, Throwable e) {
        super(message, e);
    }

    public QmonConfigurationFailedException(String message) {
        super(message);
    }
}

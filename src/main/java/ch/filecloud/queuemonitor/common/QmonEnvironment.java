package ch.filecloud.queuemonitor.common;

/**
 * Created by medo on 02/10/14.
 */
public class QmonEnvironment {

    public String key;
    public String hostname;
    public int jmxPort;
    public int order;

    public String getHostname() {
        return hostname;
    }

    public int getJmxPort() {
        return jmxPort;
    }

    public String getKey() {
        return key;
    }

    public int getOrder() {
        return order;
    }
}

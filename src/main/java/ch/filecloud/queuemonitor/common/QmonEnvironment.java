package ch.filecloud.queuemonitor.common;

/**
 * Created by medo on 02/10/14.
 */
public class QmonEnvironment implements Comparable<QmonEnvironment> {

    private final String REMOTE_JMX_URL_PREFIX = "service:jmx:rmi://localhost/jndi/rmi://";
    private final String REMOTE_JMX_URL_SUFFIX = "/jmxrmi";

    private String name;
    private String hostname;
    private int jmxPort;
    private int order;
    private String label;
    private String stage;

    public String getHostname() {
        return hostname;
    }

    public int getJmxPort() {
        return jmxPort;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public String getLabel() {
        return label;
    }

    public String getStage() {
        return stage;
    }

    public String getJmxRemoteUrl() {
        StringBuilder remoteJmxUrl = new StringBuilder(REMOTE_JMX_URL_PREFIX);
        remoteJmxUrl.append(hostname);
        remoteJmxUrl.append(":");
        remoteJmxUrl.append(jmxPort);
        remoteJmxUrl.append(REMOTE_JMX_URL_SUFFIX);

        return remoteJmxUrl.toString();
    }

    @Override
    public int compareTo(QmonEnvironment qThat) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == qThat) {
            return EQUAL;
        }

        if (this.order < qThat.order) {
            return BEFORE;
        }
        if (this.order > qThat.order) {
            return AFTER;
        }

        return EQUAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QmonEnvironment that = (QmonEnvironment) o;

        if (jmxPort != that.jmxPort) return false;
        if (order != that.order) return false;
        if (hostname != null ? !hostname.equals(that.hostname) : that.hostname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
        result = 31 * result + jmxPort;
        result = 31 * result + order;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QmonEnvironment{");
        sb.append("name='").append(name).append('\'');
        sb.append(", hostname='").append(hostname).append('\'');
        sb.append(", jmxPort=").append(jmxPort);
        sb.append(", order=").append(order);
        sb.append(", label='").append(label).append('\'');
        sb.append(", stage='").append(stage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

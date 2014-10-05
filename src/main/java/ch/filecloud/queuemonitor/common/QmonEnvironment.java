package ch.filecloud.queuemonitor.common;

/**
 * Created by medo on 02/10/14.
 */
public class QmonEnvironment implements Comparable<QmonEnvironment> {

    public String key;
    public String hostname;
    public int jmxPort;
    public int order;
    public String label;
    public String stage;

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
        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
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
        sb.append("key='").append(key).append('\'');
        sb.append(", hostname='").append(hostname).append('\'');
        sb.append(", jmxPort=").append(jmxPort);
        sb.append(", order=").append(order);
        sb.append(", label='").append(label).append('\'');
        sb.append(", stage='").append(stage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package ch.filecloud.queuemonitor.client.jmx;

import ch.filecloud.queuemonitor.common.QmonEnvironment;
import ch.filecloud.queuemonitor.common.QmonEnvironmentConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by domi on 10/10/14.
 */
@Component
public class JmxConnectionClient {

    @Inject
    private QmonEnvironmentConfiguration qmonEnvironmentConfiguration;

    private Map<String, MBeanServerConnection> mBeanServerConnections;

    public MBeanServerConnection get() {

        QmonEnvironment qmonEnvironment = qmonEnvironmentConfiguration.getCurrent();

        if(mBeanServerConnections.containsKey(qmonEnvironment.getName())) {
            return mBeanServerConnections.get(qmonEnvironment.getName());
        }

        try {
            JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL(qmonEnvironment.getJmxRemoteUrl()), new HashMap<String, Object>());
            MBeanServerConnection mBeanServerConnection = connector.getMBeanServerConnection();
            mBeanServerConnections.put(qmonEnvironment.getName(), mBeanServerConnection);
            return mBeanServerConnection;
        } catch (Exception e) {
            // throw a nice custom exception here
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void postConstruct() {
        mBeanServerConnections = new HashMap<String, MBeanServerConnection>();
    }

}

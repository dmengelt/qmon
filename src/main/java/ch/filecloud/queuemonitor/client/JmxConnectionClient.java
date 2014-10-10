package ch.filecloud.queuemonitor.client;

import ch.filecloud.queuemonitor.common.QmonEnvironment;
import ch.filecloud.queuemonitor.common.QmonEnvironmentConfiguration;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;

/**
 * Created by domi on 10/10/14.
 */
@Component
public class JmxConnectionClient {

    @Inject
    private QmonEnvironmentConfiguration qmonEnvironmentConfiguration;

    public MBeanServerConnection get() {

        try {
            QmonEnvironment qmonEnvironment = qmonEnvironmentConfiguration.getCurrent();
            JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL(qmonEnvironment.getJmxRemoteUrl()), new HashMap<String, Object>());

            return connector.getMBeanServerConnection();
        } catch (Exception e) {
            // throw a nice custom exception here
            throw new RuntimeException(e);
        }
    }

}

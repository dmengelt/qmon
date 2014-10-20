package ch.filecloud.queuemonitor.client;

import ch.filecloud.queuemonitor.client.jmx.JmxConnectionClient;
import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.JMSServerControl;

import javax.inject.Inject;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

/**
 * Created by domi on 10/20/14.
 */
public abstract class AbstractHornetQClient {

    @Inject
    protected JmxConnectionClient jmxConnectionClient;

    protected JMSServerControl getJMSServerControl() {
        try {
            ObjectName objectName = ObjectNameBuilder.DEFAULT.getJMSServerObjectName();
            return MBeanServerInvocationHandler.newProxyInstance(jmxConnectionClient.get(), objectName, JMSServerControl.class, false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object name not found!", e);
        }
    }

}

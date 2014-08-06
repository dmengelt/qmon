package ch.filecloud.queuemonitor.service;

import org.hornetq.api.core.management.ObjectNameBuilder;
import org.hornetq.api.jms.management.JMSServerControl;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

/**
 * Created by domi on 8/4/14.
 */
@Component
public abstract class ControlService {

    @Inject
    protected MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean;

    protected JMSServerControl getJMSServerControl() {
        try {
            ObjectName objectName = ObjectNameBuilder.DEFAULT.getJMSServerObjectName();
            return MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnectionFactoryBean.getObject(), objectName, JMSServerControl.class, false);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object name not found!", e);
        }
    }


}

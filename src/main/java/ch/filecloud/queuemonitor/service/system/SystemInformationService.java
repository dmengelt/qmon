package ch.filecloud.queuemonitor.service.system;

import ch.filecloud.queuemonitor.common.QmonEnvironment;
import ch.filecloud.queuemonitor.common.QmonEnvironmentConfiguration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by domi on 10/5/14.
 */
@Component
public class SystemInformationService {

    @Inject
    private QmonEnvironmentConfiguration qmonEnvironmentConfiguration;

    @Inject
    protected MBeanServerConnectionFactoryBean mBeanServerConnectionFactoryBean;

    public List<QmonEnvironment> getEnvironments() {
        return qmonEnvironmentConfiguration.getAll();
    }

    public void setCurrentEnvironment(String environmentName) {
        QmonEnvironment qmonEnvironment = qmonEnvironmentConfiguration.get(environmentName);
        try {
            mBeanServerConnectionFactoryBean.setServiceUrl(qmonEnvironment.getJmxRemoteUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

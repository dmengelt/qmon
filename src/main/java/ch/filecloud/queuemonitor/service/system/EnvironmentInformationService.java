package ch.filecloud.queuemonitor.service.system;

import ch.filecloud.queuemonitor.common.QmonEnvironment;
import ch.filecloud.queuemonitor.common.QmonEnvironmentConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author dominik.mengelt@gmail.com
 */
@Service
public class EnvironmentInformationService {

    @Inject
    private QmonEnvironmentConfiguration qmonEnvironmentConfiguration;

    public List<QmonEnvironment> getEnvironments() {
        return qmonEnvironmentConfiguration.getAll();
    }

    public void setCurrentEnvironment(String environmentName) {
        qmonEnvironmentConfiguration.setCurrent(environmentName);
    }
}

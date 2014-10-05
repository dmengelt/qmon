package ch.filecloud.queuemonitor.service.system;

import ch.filecloud.queuemonitor.common.QmonEnvironment;
import ch.filecloud.queuemonitor.common.QmonEnvironmentConfiguration;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by domi on 10/5/14.
 */
@Component
public class SystemInformationService {

    @Inject
    private QmonEnvironmentConfiguration qmonEnvironmentConfiguration;

    public List<QmonEnvironment> getEnvironments() {
        return qmonEnvironmentConfiguration.getAll();
    }
}

package ch.filecloud.queuemonitor.web.api.system;

import ch.filecloud.queuemonitor.common.QmonEnvironment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by domi on 10/5/14.
 */
public class EnvironmentInformationResponseDTO {

    public static final String ENVIRONMENTS = "environments";
    private List<QmonEnvironment> qmonEnvironments;

    public EnvironmentInformationResponseDTO(List<QmonEnvironment> qmonEnvironments) {
        this.qmonEnvironments = qmonEnvironments;
    }

    @JsonProperty(ENVIRONMENTS)
    public List<QmonEnvironment> getEnvironments() {
        return qmonEnvironments;
    }
}

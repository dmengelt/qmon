package ch.filecloud.queuemonitor.web.api.system;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by domi on 10/5/14.
 */
public class EnvironmentUpdateRequestDTO {

    private String environmentName;

    public static final String ENVIRONMENT_NAME = "environmentName";

    @JsonProperty(ENVIRONMENT_NAME)
    public String getEnvironmentName() {
        return environmentName;
    }

}

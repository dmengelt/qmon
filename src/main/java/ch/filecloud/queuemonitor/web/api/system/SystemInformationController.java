package ch.filecloud.queuemonitor.web.api.system;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static ch.filecloud.queuemonitor.common.Consts.*;

import javax.inject.Inject;

/**
 * Created by domi on 7/21/14.
 */
@Controller
@RequestMapping("/monitor/info")
public class SystemInformationController {

    public static final String JMX_REMOTE_URL_UNKNOWN = "unknown";

    @Inject
    private Environment environment;

    @RequestMapping(value = "/environment", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getEnvironmentInformation() {
        String jmxRemoteUrl = environment.getProperty(QMON_REMOTE_JMX_URL_PROPERTY);

        if(jmxRemoteUrl != null && StringUtils.hasText(jmxRemoteUrl)) {
            return jmxRemoteUrl;
        }

        return JMX_REMOTE_URL_UNKNOWN;
    }
}

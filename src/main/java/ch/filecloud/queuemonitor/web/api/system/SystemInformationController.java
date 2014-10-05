package ch.filecloud.queuemonitor.web.api.system;

import ch.filecloud.queuemonitor.service.system.SystemInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by domi on 7/21/14.
 */
@Controller
@RequestMapping("/monitor/system")
public class SystemInformationController {

    @Inject
    private SystemInformationService systemInformationService;

    @RequestMapping(value = "/environments", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SystemInformationResponseDTO getEnvironmentInformation() {
        return new SystemInformationResponseDTO(systemInformationService.getEnvironments());
    }
}

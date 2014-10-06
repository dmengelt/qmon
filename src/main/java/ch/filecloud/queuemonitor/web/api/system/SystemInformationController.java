package ch.filecloud.queuemonitor.web.api.system;

import ch.filecloud.queuemonitor.service.system.SystemInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/environments", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public void updateCurrentEnvironment(@RequestBody EnvironmentUpdateRequestDTO environmentUpdateRequestDTO) {
        systemInformationService.setCurrentEnvironment(environmentUpdateRequestDTO.getEnvironmentName());
    }
}

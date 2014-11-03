package ch.filecloud.queuemonitor.web.api.system;

import ch.filecloud.queuemonitor.service.system.EnvironmentInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by domi on 7/21/14.
 */
@Controller
@RequestMapping("/monitor/environments")
public class EnvironmentInformationController {

    @Inject
    private EnvironmentInformationService systemInformationService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public EnvironmentInformationResponseDTO getEnvironmentInformation() {
        return new EnvironmentInformationResponseDTO(systemInformationService.getEnvironments());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public void updateEnvironment(@RequestBody EnvironmentUpdateRequestDTO environmentUpdateRequestDTO) {
        systemInformationService.setCurrentEnvironment(environmentUpdateRequestDTO.getEnvironmentName());
    }
}

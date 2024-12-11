package lk.kalin.Backend.controller;

import lk.kalin.Backend.dto.ConfigurationDTO;
import lk.kalin.Backend.dto.SimData;
import lk.kalin.Backend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/config")
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;
    @PostMapping("/post")
    public ConfigurationDTO postConfig(@RequestBody ConfigurationDTO configurationDTO) {
        System.out.println(configurationDTO.toString());
        return configurationService.setConfiguration(configurationDTO);


    }

    @GetMapping("/get")
    public SimData getConfig() {
        return configurationService.getData();
    }
}

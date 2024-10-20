package com.mayo.crud.controller;

import com.mayo.crud.common.Constants;
import com.mayo.crud.model.ApplicationInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {Constants.API_BASE_CONTEXT_PATH}, produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
@Tag(name = "Application Info", description = "Operations related to get Application info")
public class ApplicationApi {
    @Value("${project.name}")
    private String name;
    @Value("${project.version}")
    private String version;
    @Autowired
    private Environment environment;

    @Operation(summary = "Get Application Version")
    @GetMapping(path = {"version"})
    public ApplicationInfo getVersion() {
        log.info("Checking Health point...{}", "Api-service");
        return ApplicationInfo.builder()
                .version(version)
                .name(name)
                .env(environment.getActiveProfiles())
                .build();
    }

}

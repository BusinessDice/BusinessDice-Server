package com.spring.server.controller;

import com.spring.server.game.BusinessCard;
import com.spring.server.game.Data;
import com.spring.server.game.ProjectCard;
import com.spring.server.game.entity.BusinessCardEntity;
import com.spring.server.game.entity.ProjectCardEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DataController {

    private final Data data = new Data();

    @ApiOperation("Returns the information about a Project Card.")
    @RequestMapping(value = "/data/project-card",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectCard> getProjectCard(@RequestParam(value = "name", defaultValue = "BAHNHOF") ProjectCardEntity card) {
        return ResponseEntity.ok(data.getProjectCard(card));
    }

    @ApiOperation("Returns the information about a Project Card.")
    @RequestMapping(value = "/data/business-card",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessCard> getBusinessCard(@RequestParam(value = "name", defaultValue = "WEIZENFELD") BusinessCardEntity card) {
        return ResponseEntity.ok(data.getBusinessCard(card));
    }

    @ApiOperation("Returns the information about all Project Cards.")
    @RequestMapping(value = "/data/all/project-card",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<ProjectCardEntity, ProjectCard>> getAllProjectCards() {
        return ResponseEntity.ok(data.getAllProjectCards());
    }

    @ApiOperation("Returns the information about all Business Cards.")
    @RequestMapping(value = "/data/all/business-card",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<BusinessCardEntity, BusinessCard>> getAllBusinessCards() {
        return ResponseEntity.ok(data.getAllBusinessCards());
    }
}

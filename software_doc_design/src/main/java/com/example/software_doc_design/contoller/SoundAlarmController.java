package com.example.software_doc_design.contoller;


import com.example.software_doc_design.domain.SoundAlarm;
import com.example.software_doc_design.service.SoundAlarmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/sound-alarms")
public class SoundAlarmController {

    private final SoundAlarmService soundAlarmService;

    public SoundAlarmController(SoundAlarmService soundAlarmService) {
        this.soundAlarmService = soundAlarmService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody
    ResponseEntity<List<SoundAlarm>> getAll() {
        List<SoundAlarm> soundAlarms = soundAlarmService.getAll();
        if (soundAlarms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(soundAlarms, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody ResponseEntity<SoundAlarm> getById(@PathVariable Long id) {
        SoundAlarm soundAlarm = soundAlarmService.getById(id);
        if (soundAlarm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(soundAlarm, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<SoundAlarm> create(@RequestBody SoundAlarm soundAlarm) {
        return new ResponseEntity<>(soundAlarmService.save(soundAlarm), HttpStatus.CREATED);
    }
}

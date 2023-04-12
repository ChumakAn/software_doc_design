package com.example.software_doc_design.contoller;

import com.example.software_doc_design.domain.Hub;
import com.example.software_doc_design.service.HubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/hubs")
@CrossOrigin
public class HubController {

    private final HubService hubService;

    public HubController(HubService hubService) {
        this.hubService = hubService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody ResponseEntity<Hub> getById(@PathVariable Long id) {
        Hub hub = hubService.getHubById(id);
        if (hub == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hub, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    public @ResponseBody ResponseEntity<List<Hub>> getByUserId(@PathVariable Long id) {
        List<Hub> hubs = hubService.getByUserId(id);
        if (hubs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hubs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Hub> create(@RequestBody Hub hub) {
        return new ResponseEntity<>(hubService.save(hub), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Hub> update(@PathVariable Long id, @RequestBody Hub hub) {
        Hub updateHub = hubService.update(id, hub);
        if (updateHub != null) {
            return new ResponseEntity<>(updateHub, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Hub> deleteById(@PathVariable Long id) {
        Hub hub = hubService.delete(id);
        if (hub == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hub, HttpStatus.OK);
    }

}

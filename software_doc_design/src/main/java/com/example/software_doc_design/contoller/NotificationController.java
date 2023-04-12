package com.example.software_doc_design.contoller;

import com.example.software_doc_design.domain.Notification;
import com.example.software_doc_design.service.NotificationService;
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
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public @ResponseBody
    ResponseEntity<List<Notification>> getAll() {
        List<Notification> notifications = notificationService.getAll();
        if (notifications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody ResponseEntity<Notification> getById(@PathVariable Long id) {
        Notification notification = notificationService.getById(id);
        if (notification == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/device/{id}")
    public @ResponseBody ResponseEntity<List<Notification>> getByDeviceId(@PathVariable Long id) {
        List<Notification> notification = notificationService.getByDeviceId(id);
        if (notification == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Notification> create(@RequestBody Notification notification) {
        return new ResponseEntity<>(notificationService.save(notification), HttpStatus.CREATED);
    }
}

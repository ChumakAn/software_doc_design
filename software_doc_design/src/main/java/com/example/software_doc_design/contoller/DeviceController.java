package com.example.software_doc_design.contoller;

import com.example.software_doc_design.domain.Device;
import com.example.software_doc_design.service.DeviceService;
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

@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody ResponseEntity<Device> getById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        if (device == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(device, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public @ResponseBody ResponseEntity<Iterable<Device>> getByUserId(@PathVariable Long id) {
        Iterable<Device> devices = deviceService.getByUserId(id);
        if (devices == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Device> create(@RequestBody Device device) {
        return new ResponseEntity<>(deviceService.save(device), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Device> update(@PathVariable Long id, @RequestBody Device device) {
        Device updatedDevice = deviceService.update(id, device);
        if (updatedDevice != null) {
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Device> deleteById(@PathVariable Long id) {
        Device deletedDevice = deviceService.delete(id);
        if (deletedDevice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(deletedDevice, HttpStatus.OK);
    }
}

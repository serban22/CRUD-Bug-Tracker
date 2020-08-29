package com.sda.finalproject.sda.Bugtracker.Controllers;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.StatusDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.StatusEntity;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.StatusConverter;
import com.sda.finalproject.sda.Bugtracker.Services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StatusController {

    @Autowired
    StatusService statusService;

    @Autowired
    StatusConverter statusConverter;

    @GetMapping(value = "/status/{statusId}", produces = "application/json")
    public ResponseEntity<StatusDTO> getStatusById(@PathVariable Integer statusId) {
        StatusEntity statusById = statusService.findByStatusId(statusId);
        if (statusById != null) {
            StatusEntity statusEntity = statusById;
            StatusDTO statusDTO = new StatusDTO(statusEntity);
            return new ResponseEntity<StatusDTO>(statusDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The status with the id " + statusId + " doesn't exist.");
        }
    }

    @GetMapping(value = "/statuses", produces = "application/json")
    public ResponseEntity<List<StatusDTO>> getAllStatuses() {
        List<StatusDTO> statuses = statusService.getAllStatuses();
        return new ResponseEntity<List<StatusDTO>>(statuses, HttpStatus.OK);
    }

    @PostMapping(value = "/saveStatusWithConversion", consumes = "application/json")
    public ResponseEntity<StatusEntity> saveStatusWithConversion(@RequestBody StatusDTO statusDTO) {
        StatusEntity statusEntity = statusService.saveStatusWithConversion(statusDTO);
        return new ResponseEntity<StatusEntity>(statusEntity, HttpStatus.OK);
    }
}

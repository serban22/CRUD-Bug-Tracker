package com.sda.finalproject.sda.Bugtracker.Services;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.StatusDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.StatusEntity;
import com.sda.finalproject.sda.Bugtracker.Repositories.StatusRepository;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.StatusConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    StatusConverter statusConverter;

    @Transactional
    public StatusEntity findByStatusId(Integer id) {
        Optional<StatusEntity> byId = statusRepository.findByStatusId(id);

        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("The status with the id " + id + " could not be found.");
        }
    }

    @Transactional
    public StatusEntity findByStatusName(String statusName) {
        Optional<StatusEntity> byName = statusRepository.findByStatusName(statusName);

        if (byName.isPresent()) {
            return byName.get();
        } else {
            throw new RuntimeException("The status with the id " + statusName + " could not be found.");
        }
    }

    @Transactional
    public List<StatusDTO> getAllStatuses() {
        List<StatusEntity> allStatuses = statusRepository.findAll();
        List<StatusDTO> statusDTOS = new ArrayList<>();

        for (StatusEntity statusEntity : allStatuses) {
            StatusDTO tempStatus = new StatusDTO();
            tempStatus.statusId = statusEntity.getStatusId();
            tempStatus.statusName = statusEntity.getStatusName();
            tempStatus.projectStatus = statusEntity.getStatusName();
            statusDTOS.add(tempStatus);
        }
        return statusDTOS;
    }

    @Transactional
    public void saveStatus(StatusEntity status) {
        statusRepository.save(status);
    }

    @Transactional
    public void deleteStatusById(Integer id) {
        statusRepository.deleteById(id);
    }

    @Transactional
    public StatusEntity saveStatusWithConversion(StatusDTO statusDTO) {
        StatusEntity statusEntityToSave = statusConverter.convertToEntity(statusDTO);
        return statusEntityToSave;
    }
}

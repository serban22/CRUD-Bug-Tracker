package com.sda.finalproject.sda.Bugtracker.Services.Converters;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.StatusDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.StatusEntity;
import org.springframework.stereotype.Service;

@Service
public class StatusConverter implements EntityConverter<StatusEntity, StatusDTO> {

    @Override
    public StatusDTO convertToDTO(StatusEntity statusEntity) {
        StatusDTO dto = new StatusDTO();

        dto.statusId = statusEntity.getStatusId();
        dto.statusName = statusEntity.getStatusName();
        return dto;
    }

    @Override
    public StatusEntity convertToEntity(StatusDTO statusDTO) {
        StatusEntity statusEntity = new StatusEntity();

        statusEntity.setStatusName(statusDTO.statusName);
        return statusEntity;
    }
}

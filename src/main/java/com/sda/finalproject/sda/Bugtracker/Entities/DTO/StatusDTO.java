package com.sda.finalproject.sda.Bugtracker.Entities.DTO;

import com.sda.finalproject.sda.Bugtracker.Entities.StatusEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusDTO {

    public Integer statusId;
    public String statusName;
    public String projectStatus;

    public StatusDTO(StatusEntity statusEntity) {
    }
}

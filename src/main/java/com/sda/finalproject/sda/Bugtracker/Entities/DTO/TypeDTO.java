package com.sda.finalproject.sda.Bugtracker.Entities.DTO;

import com.sda.finalproject.sda.Bugtracker.Entities.TypeEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeDTO {

    public Integer typeId;
    public String typeName;

    public TypeDTO(TypeEntity typeEntity) {
    }
}

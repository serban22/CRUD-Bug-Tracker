package com.sda.finalproject.sda.Bugtracker.Services.Converters;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.TypeDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.TypeEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeConverter implements EntityConverter<TypeEntity, TypeDTO> {

    @Override
    public TypeDTO convertToDTO(TypeEntity typeEntity) {
        TypeDTO dto = new TypeDTO();

        dto.typeId = typeEntity.getTypeId();
        dto.typeName = typeEntity.getTypeName();
        return dto;
    }

    @Override
    public TypeEntity convertToEntity(TypeDTO typeDTO) {
        TypeEntity typeEntity = new TypeEntity();

        typeEntity.setTypeName(typeDTO.typeName);
        return typeEntity;
    }
}

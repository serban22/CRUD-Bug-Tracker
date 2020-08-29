package com.sda.finalproject.sda.Bugtracker.Services.Converters;

public interface EntityConverter<ENTITY, DTO> {

    public DTO convertToDTO(ENTITY entity);
    public ENTITY convertToEntity(DTO dto);
}

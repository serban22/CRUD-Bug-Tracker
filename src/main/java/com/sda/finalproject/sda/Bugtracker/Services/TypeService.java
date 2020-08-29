package com.sda.finalproject.sda.Bugtracker.Services;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.TypeDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.TypeEntity;
import com.sda.finalproject.sda.Bugtracker.Repositories.TypeRepository;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    TypeConverter typeConverter;

    @Transactional
    public Optional<TypeEntity> findByTypeId(Integer id) {
        return typeRepository.findByTypeId(id);
    }

    @Transactional
    public Optional<TypeEntity> findByTypeName(String typeName) {
        return typeRepository.findByTypeName(typeName);
    }

    @Transactional
    public void saveType(TypeEntity typeEntity) {
        typeRepository.save(typeEntity);
    }

    @Transactional
    public List<TypeDTO> getAllTypes() {
        List<TypeEntity> allTypes = typeRepository.findAll();
        List<TypeDTO> typeDTOS = new ArrayList<>();

        for (TypeEntity typeEntity : allTypes) {
            TypeDTO tempType = new TypeDTO();
            tempType.typeId = typeEntity.getTypeId();
            tempType.typeName = typeEntity.getTypeName();
            typeDTOS.add(tempType);
        }
        return typeDTOS;
    }

    @Transactional
    public void deleteTypeById(Integer id) {
        typeRepository.deleteById(id);
    }

    @Transactional
    public TypeEntity saveTypeWithConversion(TypeDTO typeDTO) {
        TypeEntity typeEntityToSave = typeConverter.convertToEntity(typeDTO);
        return typeEntityToSave;
    }
}

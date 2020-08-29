package com.sda.finalproject.sda.Bugtracker.Controllers;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.TypeDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.TypeEntity;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.TypeConverter;
import com.sda.finalproject.sda.Bugtracker.Services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TypeController {

    @Autowired
    TypeService typeService;

    @Autowired
    TypeConverter typeConverter;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping(value = "/types", produces = "application/json")
    public ResponseEntity<List<TypeDTO>> getAllTypes() {
        List<TypeDTO> types = typeService.getAllTypes();
        return new ResponseEntity<List<TypeDTO>>(types, HttpStatus.OK);
    }

    @PostMapping(value = "/saveTypeWithConversion", consumes = "application/json")
    public ResponseEntity<TypeEntity> saveTypeWithConversion(@RequestBody TypeDTO typeDTO) {
        TypeEntity typeEntity = typeService.saveTypeWithConversion(typeDTO);
        return new ResponseEntity<TypeEntity>(typeEntity, HttpStatus.OK);
    }
}

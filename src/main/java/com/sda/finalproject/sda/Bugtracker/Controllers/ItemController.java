package com.sda.finalproject.sda.Bugtracker.Controllers;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ItemDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.ItemEntity;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.ItemConverter;
import com.sda.finalproject.sda.Bugtracker.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemConverter itemConverter;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/item/id/{itemId}", produces = "application/json")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Integer itemId) {
        ItemEntity itemById = itemService.findByItemId(itemId);
        if (itemById != null) {
            ItemEntity itemEntity = itemById;
            ItemDTO itemDTO = new ItemDTO(itemEntity);
            return new ResponseEntity<ItemDTO>(itemDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The item with the id " + itemId + " doesn't exist.");
        }
    }

    @GetMapping(value = "/items", produces = "application/json")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> items = itemService.getAllItems();
        return new ResponseEntity<List<ItemDTO>>(items, HttpStatus.OK);
    }

    @PostMapping(value = "create-new-item", consumes = "application/json")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemDTO newItem) {
        ItemEntity itemEntity = itemService.saveItem(newItem);
        return new ResponseEntity<ItemEntity>(itemEntity, HttpStatus.OK);
    }

    @PostMapping(value = "save-item-with-conversion", consumes = "application/json")
    public ResponseEntity<ItemEntity> saveItemWithConversion(@RequestBody ItemDTO itemDTO) {
        ItemEntity itemEntity = itemService.saveItemWithConversion(itemDTO);
        return new ResponseEntity<ItemEntity>(itemEntity, HttpStatus.OK);
    }

    @PutMapping(value = "/update-item/{itemId}", consumes = "application/json")
    public ResponseEntity<ItemEntity> updateProjectById(@PathVariable(name = "itemId") Integer itemId, @RequestBody ItemDTO itemDTO) {
        ItemEntity itemEntity = itemService.findByItemId(itemId);
        if (itemEntity == null) {
            ItemEntity anotherItemEntity = itemService.updateByItemId(itemId, itemDTO);
            return new ResponseEntity<ItemEntity>(anotherItemEntity, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The item with the id " + itemId + " doesn't exist.");
        }
    }

    @DeleteMapping("/item/{itemId}")
    public void deleteByProjectId(@PathVariable Integer itemId) {
        if (itemService.isItemIdPresent(itemId)) {
            itemService.deleteByItemId(itemId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The item with the id " + itemId + " doesn't exist.");
        }
    }
}

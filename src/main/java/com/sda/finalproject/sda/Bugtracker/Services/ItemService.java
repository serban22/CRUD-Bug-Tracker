package com.sda.finalproject.sda.Bugtracker.Services;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ItemDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.ItemEntity;
import com.sda.finalproject.sda.Bugtracker.Entities.StatusEntity;
import com.sda.finalproject.sda.Bugtracker.Entities.TypeEntity;
import com.sda.finalproject.sda.Bugtracker.Repositories.ItemRepository;
import com.sda.finalproject.sda.Bugtracker.Repositories.ProjectRepository;
import com.sda.finalproject.sda.Bugtracker.Repositories.StatusRepository;
import com.sda.finalproject.sda.Bugtracker.Repositories.TypeRepository;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.ItemConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    ItemConverter itemConverter;

    @Transactional
    public ItemEntity findByItemId(Integer id) {
        Optional<ItemEntity> byId = itemRepository.findByItemId(id);

        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("Could not find an item with the id: " + id);
        }
    }

    @Transactional
    public Boolean isItemIdPresent(Integer id) {
        return itemRepository.findById(id).isPresent();
    }

    @Transactional
    public List<ItemEntity> findByName (String name) {
        return itemRepository.findByItemName(name);
    }

    @Transactional
    public ItemEntity saveItem(ItemDTO itemDTO) {

        Optional<StatusEntity> byStatusName = statusRepository.findByStatusName(itemDTO.itemStatus);
        Optional<TypeEntity> byTypeName = typeRepository.findByTypeName(itemDTO.itemType);

        StatusEntity statusEntity = null;
        if (!byStatusName.isPresent()) {
            StatusEntity newStatus = new StatusEntity(itemDTO.itemStatus);
            statusEntity = statusRepository.save(newStatus);
        } else {
            statusEntity = byStatusName.get();
        }

        TypeEntity typeEntity = null;
        if (!byTypeName.isPresent()) {
            TypeEntity newType = new TypeEntity(itemDTO.itemType);
            typeEntity = typeRepository.save(newType);
        } else {
            typeEntity = byTypeName.get();
        }

        ItemEntity itemEntityToBeSaved = new ItemEntity(itemDTO, statusEntity, typeEntity);
        ItemEntity savedItem = itemRepository.save(itemEntityToBeSaved);
        return savedItem;
    }

    public ItemEntity updateByItemId(Integer id, ItemDTO itemToBeUpdated) {

        ItemEntity itemEntity;
        StatusEntity statusEntity;
        TypeEntity typeEntity;

        Optional<StatusEntity> statusEntityOptional = statusRepository.findByStatusName(itemToBeUpdated.itemStatus);
        Optional<TypeEntity> typeEntityOptional = typeRepository.findByTypeName(itemToBeUpdated.itemType);

        Optional<ItemEntity> itemEntityOptional = itemRepository.findById(id);
        if (!itemEntityOptional.isPresent()) {
            throw new RuntimeException("The item with the id " + id + " could not be found.");
        } else {
            itemEntity = itemEntityOptional.get();
            statusEntity = statusEntityOptional.get();
            typeEntity = typeEntityOptional.get();

            statusEntity.setStatusName(itemToBeUpdated.itemStatus);
            typeEntity.setTypeName(itemToBeUpdated.itemType);

            itemEntity.setItemName(itemToBeUpdated.itemName);
            itemEntity.setItemStatus(statusEntity);
            itemEntity.setItemType(typeEntity);
        }
        return itemRepository.save(itemEntity);
    }

    public List<ItemDTO> getAllItems() {
        List<ItemEntity> allItems = itemRepository.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (ItemEntity itemEntity : allItems) {
            ItemDTO tempItem = new ItemDTO();
            tempItem.itemId = itemEntity.getItemId();
            tempItem.itemName = itemEntity.getItemName();
            tempItem.itemStatus = itemEntity.getItemStatus().getStatusName();
            tempItem.itemType = itemEntity.getItemType().getTypeName();

            itemDTOS.add(tempItem);
        }
        return itemDTOS;
    }

    @Transactional
    public void deleteByItemId(Integer id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public ItemEntity saveItemWithConversion(ItemDTO itemDTO) {
        ItemEntity itemEntityToSave = itemConverter.convertToEntity(itemDTO);
        return itemEntityToSave;
    }
}

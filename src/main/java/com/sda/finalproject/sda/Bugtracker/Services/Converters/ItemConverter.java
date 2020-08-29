package com.sda.finalproject.sda.Bugtracker.Services.Converters;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ItemDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.ItemEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemConverter implements EntityConverter<ItemEntity, ItemDTO> {

    @Override
    public ItemDTO convertToDTO(ItemEntity itemEntity) {

        ItemDTO dto = new ItemDTO();
        dto.itemId = itemEntity.getItemId();
        dto.itemName = itemEntity.getItemName();
        dto.itemStatus = itemEntity.getItemStatus().getStatusName();
        dto.itemType = itemEntity.getItemType().getTypeName();

        if (itemEntity.getItemStatus() != null && itemEntity.getItemType() != null) {
            dto.itemStatus = itemEntity.getItemStatus().getStatusName();
            dto.itemType = itemEntity.getItemType().getTypeName();
        } else if (itemEntity.getItemStatus() == null) {
            dto.itemStatus = "Undefined status.";
        } else if (itemEntity.getItemType() == null) {
            dto.itemType = "Undefined type.";
        }
        return dto;
    }

    @Override
    public ItemEntity convertToEntity(ItemDTO itemDTO) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemName(itemDTO.itemName);

        return itemEntity;
    }
}

package com.sda.finalproject.sda.Bugtracker.Entities.DTO;

import com.sda.finalproject.sda.Bugtracker.Entities.ItemEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {

    public Integer itemId;
    public String itemName;
    public String itemStatus;
    public String itemType;

    public ItemDTO(ItemEntity itemEntity) {
        this.itemId = itemEntity.getItemId();
        this.itemName = itemEntity.getItemName();
        this.itemStatus = itemEntity.getItemStatus().getStatusName();
        this.itemType = itemEntity.getItemType().getTypeName();
    }
}

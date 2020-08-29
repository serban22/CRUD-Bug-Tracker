package com.sda.finalproject.sda.Bugtracker.Entities;

import javax.persistence.*;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ItemDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    Integer itemId;

    @Column(name = "item_name")
    String itemName;

    @ManyToOne
    @JoinColumn(name = "status_id")
    StatusEntity itemStatus;

    @ManyToOne
    @JoinColumn(name = "type_id")
    TypeEntity itemType;

    public ItemEntity(ItemDTO itemDTO, StatusEntity statusEntity, TypeEntity typeEntity) {
    }
}

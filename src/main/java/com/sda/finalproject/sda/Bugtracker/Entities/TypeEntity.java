package com.sda.finalproject.sda.Bugtracker.Entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "type")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    Integer typeId;

    @Column(name = "type_name")
    String typeName;

    @OneToMany(mappedBy = "itemType")
    List<ItemEntity> items;

    public TypeEntity(String itemType) {
        this.typeName = itemType;
    }
}

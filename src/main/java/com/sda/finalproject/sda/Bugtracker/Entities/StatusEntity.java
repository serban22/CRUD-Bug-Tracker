package com.sda.finalproject.sda.Bugtracker.Entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    Integer statusId;

    @Column(name = "status_name")
    String statusName;

    @OneToMany(mappedBy = "projectStatusName")
    List<ProjectEntity> projects;

    @OneToMany(mappedBy = "itemStatus")
    List<ItemEntity> items;

    public StatusEntity(String itemStatus) {
        this.statusName = itemStatus;
    }
}

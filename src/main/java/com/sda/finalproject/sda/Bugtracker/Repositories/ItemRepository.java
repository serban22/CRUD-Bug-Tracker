package com.sda.finalproject.sda.Bugtracker.Repositories;

import com.sda.finalproject.sda.Bugtracker.Entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    Optional<ItemEntity> findByItemId(Integer id);
    List<ItemEntity> findByItemName(String title);
}

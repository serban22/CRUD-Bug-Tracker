package com.sda.finalproject.sda.Bugtracker.Repositories;

import com.sda.finalproject.sda.Bugtracker.Entities.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Integer> {

    Optional<TypeEntity> findByTypeId(Integer id);
    Optional<TypeEntity> findByTypeName(String name);
}

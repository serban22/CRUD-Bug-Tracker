package com.sda.finalproject.sda.Bugtracker.Repositories;

import com.sda.finalproject.sda.Bugtracker.Entities.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {

    Optional<StatusEntity> findByStatusId(Integer id);
    Optional<StatusEntity> findByStatusName(String name);
}

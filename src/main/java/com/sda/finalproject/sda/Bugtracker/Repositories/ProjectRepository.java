package com.sda.finalproject.sda.Bugtracker.Repositories;

import com.sda.finalproject.sda.Bugtracker.Entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    List<ProjectEntity> findByProjectName(String projectName);
    Optional<ProjectEntity> findProjectEntityByProjectId(Integer id);

    @Query(value = "select * from bugtrackingsoftware.project where project_id =:status_id", nativeQuery = true)
    List<ProjectEntity> findAllProjectsByStatusId(@Param("status_id") Integer statusId);
}

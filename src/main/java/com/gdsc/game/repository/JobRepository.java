package com.gdsc.game.repository;

import com.gdsc.game.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j LEFT JOIN FETCH j.availableSkills WHERE j.name = :name")
    Optional<Job> findByNameWithSkills(@Param("name") String name);
    Optional<Job> findByName(String name);
}

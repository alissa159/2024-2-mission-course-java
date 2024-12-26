package com.gdsc.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("SELECT c FROM Character c LEFT JOIN FETCH c.skills WHERE c.name = :name")
    Optional<Character> findByNameWithSkills(@Param("name") String name);

    @Query("SELECT c FROM Character c WHERE c.currentHp > 0 AND c.job.name = :jobName")
    List<Character> findAliveCharactersByJob(@Param("jobName") String jobName);

    Optional<Character> findByName(String name);
}

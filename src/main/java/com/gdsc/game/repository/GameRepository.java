package com.gdsc.game.repository;

import com.gdsc.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g LEFT JOIN FETCH g.characters WHERE g.id = :id")
    Optional<Game> findGameWithCharacters(@Param("id") Long id);

    @Query("SELECT g FROM Game g WHERE g.isGameOver = false")
    List<Game> findActiveGames();
}
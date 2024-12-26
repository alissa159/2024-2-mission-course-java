package com.gdsc.game.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private int totalTurns;
    private int currentTurn;
    private boolean isGameOver;

    @OneToMany
    private List<Character> characters;

    public void nextTurn() {
        currentTurn++;
        if (currentTurn > totalTurns) {
            isGameOver = true;
        }
        characters.forEach(character ->
                character.getSkills().forEach(Skill::updateCooldown)
        );
    }
}
package com.gdsc.game.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int manaCost;
    private int cooldown;
    private int currentCooldown;
    private int minDamage;
    private int maxDamage;

    @ManyToOne
    private Job job;

    @ManyToOne
    private Character character;

    public int use() {
        if (currentCooldown > 0 || character.getMp() < manaCost) {
            throw new IllegalStateException("Cannot use skill");
        }

        character.setMp(character.getMp() - manaCost);
        currentCooldown = cooldown;

        return new Random().nextInt(maxDamage - minDamage + 1) + minDamage;
    }

    public void updateCooldown() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }
}

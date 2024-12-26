package com.gdsc.game.domain;

import com.gdsc.game.dto.CharacterStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Character {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int level;
    private int currentHp;
    private int maxHp;
    private int currentMp;
    private int maxMp;

    @ManyToOne
    private Job job;

    @OneToMany(mappedBy = "character")
    private List<Skill> skills;

    public Character(String name, int level, Job job) {
        this.name = name;
        this.level = level;
        this.job = job;
        this.maxHp = job.calculateHp(level);
        this.maxMp = job.calculateMp(level);
        this.currentHp = maxHp;
        this.currentMp = maxMp;
    }
    protected Character() {}

    public int getMp() {
        return this.currentMp;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setMp(int mp) {
        this.currentMp = Math.max(0, Math.min(mp, maxMp));
    }

    public void setHp(int hp) {
        this.currentHp = Math.max(0, Math.min(hp, maxHp));
    }

    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, this.currentHp - damage);
    }

    public void useMp(int amount) {
        if (currentMp < amount) {
            throw new IllegalStateException("Not enough MP");
        }
        this.currentMp -= amount;
    }
    public CharacterStatus getStatus() {
        return new CharacterStatus(name, currentHp, currentMp, job.getName(), level);
    }
    public static Character create(String name, int level, Job job) {
        return new Character(name, level, job);
    }
}

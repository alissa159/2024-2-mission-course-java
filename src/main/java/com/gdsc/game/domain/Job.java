package com.gdsc.game.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int baseHp;
    private int baseMp;

    @OneToMany(mappedBy = "job")
    private List<Skill> availableSkills;

    protected Job() {}

    public Job(String name, int baseHp, int baseMp) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseMp = baseMp;
    }

    public int calculateHp(int level) {
        return baseHp + (level * 10);
    }

    public int calculateMp(int level) {
        return baseMp + (level * 5);
    }

    public String getName() {
        return this.name;
    }
}

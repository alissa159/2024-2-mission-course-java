package com.gdsc.game;

import java.util.Random;

public class Skill {
    private String name;
    private int manaCost;
    private int cooldown;

    // 생성자
    public Skill(String name, int manaCost, int cooldown) {
        this.name = name;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
    }
}

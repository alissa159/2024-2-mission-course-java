package com.gdsc.game.dto;

import lombok.Data;

@Data
public class ActionDTO {
    private String name;
    private int manaCost;
    private int cooldown;
}
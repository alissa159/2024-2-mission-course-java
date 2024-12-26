package com.gdsc.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterStatus {
    private String name;
    private int currentHp;
    private int currentMp;
    private String jobName;
    private int level;
}
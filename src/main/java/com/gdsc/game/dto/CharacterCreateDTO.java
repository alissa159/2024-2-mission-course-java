package com.gdsc.game.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CharacterCreateDTO {
    @NotNull
    private String name;
    @NotNull private String job;
    @Min(1) private int level;
}
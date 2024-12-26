package com.gdsc.game.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GameStartRequest {
    @NotNull
    private List<CharacterCreateDTO> characters;

    @Min(1)
    private int turns;
}
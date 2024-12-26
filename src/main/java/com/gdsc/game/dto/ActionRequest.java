package com.gdsc.game.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActionRequest {
    @NotNull
    private String actionName;
}
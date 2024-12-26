package com.gdsc.game.service;

import com.gdsc.game.domain.Game;
import com.gdsc.game.dto.ActionDTO;
import com.gdsc.game.dto.ActionResultDTO;
import com.gdsc.game.dto.CharacterCreateDTO;
import com.gdsc.game.dto.CharacterStatus;

import java.util.List;

public interface GameService {
    Game initializeGame(List<CharacterCreateDTO> characters, int turns);
    ActionResultDTO processAction(String characterName, String actionName);
    CharacterStatus getCharacterStatus(String name);
    List<ActionDTO> getAvailableActions(String name, String sortBy);
}

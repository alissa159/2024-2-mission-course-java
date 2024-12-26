package com.gdsc.game.service;

import com.gdsc.game.domain.Character;
import com.gdsc.game.domain.Game;
import com.gdsc.game.domain.Job;
import com.gdsc.game.dto.ActionDTO;
import com.gdsc.game.dto.ActionResultDTO;
import com.gdsc.game.dto.CharacterCreateDTO;
import com.gdsc.game.dto.CharacterStatus;
import com.gdsc.game.exception.NotFoundException;
import com.gdsc.game.repository.CharacterRepository;
import com.gdsc.game.repository.GameRepository;
import com.gdsc.game.repository.JobRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final CharacterRepository characterRepository;
    private final JobRepository jobRepository;

    @Override
    @Transactional
    public Game initializeGame(List<CharacterCreateDTO> characters, int turns) {
        if (characters.size() != 2) {
            throw new IllegalArgumentException("Game requires exactly 2 characters");
        }

        Game game = new Game();
        game.setTotalTurns(turns);
        game.setCurrentTurn(0);

        List<Character> gameCharacters = characters.stream()
                .map(this::createCharacter)
                .collect(Collectors.toList());

        game.setCharacters(gameCharacters);
        return gameRepository.save(game);
    }

    private Character createCharacter(CharacterCreateDTO dto) {
        Job job = jobRepository.findByName(dto.getJob())
                .orElseThrow(() -> new NotFoundException("Job not found"));

        Character character= new Character();
        character.setName(dto.getName());
        character.setLevel(dto.getLevel());
        character.setJob(job);
        character.setHp(job.calculateHp(dto.getLevel()));
        character.setMp(job.calculateMp(dto.getLevel()));

        return characterRepository.save(character);
    }

    private Character createCharacter(CharacterCreateDTO dto) {
        Job job = jobRepository.findByName(dto.getJob())
                .orElseThrow(() -> new NotFoundException("Job not found"));

        return characterRepository.save(Character.create(dto.getName(), dto.getLevel(), job));
    }

    @Override
    public ActionResultDTO processAction(String characterName, String actionName) {
        Character character = characterRepository.findByName(characterName)
                .orElseThrow(() -> new NotFoundException("Character not found"));

        // 액션 처리 로직 구현
        return new ActionResultDTO(); // 실제 구현 필요
    }

    @Override
    public CharacterStatus getCharacterStatus(String name) {
        return characterRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Character not found"))
                .getStatus();
    }

    @Override
    public List<ActionDTO> getAvailableActions(String name, String sortBy) {
        Character character = characterRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Character not found"));

        // 가능한 액션 목록 반환 로직 구현
        return List.of(); // 실제 구현 필요
    }

}

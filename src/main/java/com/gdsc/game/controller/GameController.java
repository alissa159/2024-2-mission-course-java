package com.gdsc.game.controller;

import com.gdsc.game.domain.Game;
import com.gdsc.game.dto.*;
import com.gdsc.game.exception.NotFoundException;
import com.gdsc.game.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Game> startGame(@RequestBody @Valid GameStartRequest request) {
        try {
            Game game = gameService.initializeGame(request.getCharacters(), request.getTurns());
            return ResponseEntity.ok(game);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/character/{name}/status")
    public ResponseEntity<CharacterStatus> getCharacterStatus(@PathVariable String name) {
        try {
            CharacterStatus status = gameService.getCharacterStatus(name);
            return ResponseEntity.ok(status);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/character/{name}/actions")
    public ResponseEntity<List<ActionDTO>> getAvailableActions(
            @PathVariable String name,
            @RequestParam(defaultValue = "name") String sortBy) {
        try {
            List<ActionDTO> actions = gameService.getAvailableActions(name, sortBy);
            return ResponseEntity.ok(actions);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/character/{name}/action")
    public ResponseEntity<ActionResultDTO> performAction(
            @PathVariable String name,
            @RequestBody @Valid ActionRequest request) {
        try {
            ActionResultDTO result = gameService.processAction(name, request.getActionName());
            return ResponseEntity.ok(result);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.example.game_library_jpa.controller;

import com.example.game_library_jpa.model.Game;
import com.example.game_library_jpa.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<?> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        return gameService.getGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addGame(@RequestBody Game game) {
        Optional<Game> savedGame = gameService.addGame(game);

        if (savedGame.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid game data or duplicate title/platform.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Optional<Game> updatedGame = gameService.updateGame(id, game);

        if (updatedGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedGame.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        boolean deleted = gameService.deleteGame(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/platform/{platform}")
    public ResponseEntity<?> getGamesByPlatform(@PathVariable String platform) {
        return ResponseEntity.ok(gameService.getGamesByPlatform(platform));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<?> getGamesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(gameService.getGamesByGenre(genre));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchGamesByTitle(@RequestParam String title) {
        return ResponseEntity.ok(gameService.searchGamesByTitle(title));
    }

    @GetMapping("/titles")
    public ResponseEntity<?> getAllGameTitles() {
        return ResponseEntity.ok(gameService.getAllGameTitles());
    }

    @GetMapping("/count/platform/{platform}")
    public ResponseEntity<?> countGamesByPlatform(@PathVariable String platform) {
        return ResponseEntity.ok(gameService.countGamesByPlatform(platform));
    }
}
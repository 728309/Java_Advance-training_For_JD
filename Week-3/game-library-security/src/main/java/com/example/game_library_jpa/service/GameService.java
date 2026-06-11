package com.example.game_library_jpa.service;

import com.example.game_library_jpa.model.Game;
import com.example.game_library_jpa.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Optional<Game> addGame(Game game) {
        if (isInvalid(game)) {
            return Optional.empty();
        }

        boolean alreadyExists = gameRepository.existsByTitleIgnoreCaseAndPlatformIgnoreCase(
                game.getTitle(),
                game.getPlatform()
        );

        if (alreadyExists) {
            return Optional.empty();
        }

        Game savedGame = gameRepository.save(game);
        return Optional.of(savedGame);
    }

    public Optional<Game> updateGame(Long id, Game updatedGame) {
        if (isInvalid(updatedGame)) {
            return Optional.empty();
        }

        Optional<Game> existingGameOptional = gameRepository.findById(id);

        if (existingGameOptional.isEmpty()) {
            return Optional.empty();
        }

        Game existingGame = existingGameOptional.get();
        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setGenre(updatedGame.getGenre());
        existingGame.setPlatform(updatedGame.getPlatform());

        Game savedGame = gameRepository.save(existingGame);
        return Optional.of(savedGame);
    }

    public boolean deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            return false;
        }

        gameRepository.deleteById(id);
        return true;
    }

    private boolean isInvalid(Game game) {
        return game.getTitle() == null || game.getTitle().isBlank()
                || game.getGenre() == null || game.getGenre().isBlank()
                || game.getPlatform() == null || game.getPlatform().isBlank();
    }
}
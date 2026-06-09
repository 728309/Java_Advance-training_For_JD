package com.example.game_library_api.service;

import com.example.game_library_api.model.Game;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final List<Game> games = new ArrayList<>();
    private Long nextId = 1L;

    public List<Game> getAllGames() {
        return games;
    }

    public Optional<Game> getGameById(Long id) {
        return games.stream()
                .filter(game -> game.getId().equals(id))
                .findFirst();
    }

    public Optional<Game> addGame(Game game) {
        if (isInvalid(game)) {
            return Optional.empty();
        }

        boolean alreadyExists = games.stream()
                .anyMatch(existingGame ->
                        existingGame.getTitle().equalsIgnoreCase(game.getTitle())
                                && existingGame.getPlatform().equalsIgnoreCase(game.getPlatform())
                );

        if (alreadyExists) {
            return Optional.empty();
        }

        game.setId(nextId);
        nextId++;
        games.add(game);

        return Optional.of(game);
    }

    public Optional<Game> updateGame(Long id, Game updatedGame) {
        if (isInvalid(updatedGame)) {
            return Optional.empty();
        }

        Optional<Game> existingGameOptional = getGameById(id);

        if (existingGameOptional.isEmpty()) {
            return Optional.empty();
        }

        Game existingGame = existingGameOptional.get();
        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setGenre(updatedGame.getGenre());
        existingGame.setPlatform(updatedGame.getPlatform());

        return Optional.of(existingGame);
    }

    public boolean deleteGame(Long id) {
        return games.removeIf(game -> game.getId().equals(id));
    }

    private boolean isInvalid(Game game) {
        return game.getTitle() == null || game.getTitle().isBlank()
                || game.getGenre() == null || game.getGenre().isBlank()
                || game.getPlatform() == null || game.getPlatform().isBlank();
    }
}
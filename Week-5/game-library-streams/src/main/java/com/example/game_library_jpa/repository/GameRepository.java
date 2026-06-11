package com.example.game_library_jpa.repository;

import com.example.game_library_jpa.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByTitleIgnoreCaseAndPlatformIgnoreCase(String title, String platform);
}
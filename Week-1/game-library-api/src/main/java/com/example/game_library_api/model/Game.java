package com.example.game_library_api.model;

public class Game {
    private Long id;
    private String title;
    private String genre;
    private String platform;

    public Game() {
    }

    public Game(Long id, String title, String genre, String platform) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.platform = platform;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
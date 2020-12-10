package com.codecta.academy2021.disneylands.netflix;

public class Movie {
    public String id;
    public String title;
    public int year;
    public String genre;
    public String description;
    public String director;

    public Movie(){}
    public Movie(String id, String title, int year, String genre, String description, String director) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.director = director;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }
}
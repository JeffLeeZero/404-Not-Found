package com.bean;

public class RecommendBean {
    private String movieId = null;
    private String movieName = null;
    private double movieScore;
    private String movieSlogan = null;
    private String moviePoster = null;
    private String movieBackground = null;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(double movieScore) {
        this.movieScore = movieScore;
    }

    public String getMovieSlogan() {
        return movieSlogan;
    }

    public void setMovieSlogan(String movieSlogan) {
        this.movieSlogan = movieSlogan;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieBackground() {
        return movieBackground;
    }

    public void setMovieBackground(String movieBackground) {
        this.movieBackground = movieBackground;
    }
}

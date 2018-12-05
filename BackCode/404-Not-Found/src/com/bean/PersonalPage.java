package com.bean;

import java.util.ArrayList;
import java.util.List;

public class PersonalPage {
    private String nickname;
    private List<FavMovieBean> favMovie = new ArrayList <>();
    private List<CommentBean> commentedMovie = new ArrayList <>();

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List <FavMovieBean> getFavMovie() {
        return favMovie;
    }

    public void setFavMovie(List <FavMovieBean> favMovie) {
        this.favMovie = favMovie;
    }

    public List <CommentBean> getCommentedMovie() {
        return commentedMovie;
    }

    public void setCommentedMovie(List <CommentBean> commentedMovie) {
        this.commentedMovie = commentedMovie;
    }
}

package com.dao;

import com.DBUtil.DBUtil;
import com.bean.CommentBean;
import com.bean.FavMovieBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalPageDao {
    //用户名
    public String getNickname(String reqId) throws SQLException {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, reqId);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        String nickname = null;
        if (resultSet.next()) {
            nickname = resultSet.getString("account_nickname");
        }
        conn.commit();
        conn.close();
        return nickname;
    }

    //喜欢的电影
    public List<FavMovieBean> favMovie(String reqId) throws SQLException {
        String firstSql = "SELECT * FROM favourite WHERE account_id = ? AND is_favourite = 1";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(firstSql);
        preparedStatement.setString(1, reqId);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet firstResult = preparedStatement.executeQuery();
        List<String> movieId = new ArrayList <>();

        while (firstResult.next()) {
            String id = firstResult.getString("movie_id");
            movieId.add(id);
        }
        conn.commit();

        List<FavMovieBean> details = new ArrayList <>();
        for (String id:movieId) {
            FavMovieBean detail = new FavMovieBean();
            String getDetail = "SELECT * FROM movie WHERE movie_id =?";
            PreparedStatement getIdStatement = conn.prepareStatement(getDetail);
            getIdStatement.setString(1, id);
            getIdStatement.execute();
            //执行查询语句并返回结果集
            ResultSet resultSet = getIdStatement.executeQuery();
            resultSet.next();
            detail.setMovieName(resultSet.getString("movie_name"));
            detail.setFavPic(resultSet.getString("fav_pic"));
            details.add(detail);
            conn.commit();
        }
        conn.close();
        return details;
    }

    //评论过的电影
    public List<CommentBean> commentedMovie(String reqId) throws SQLException {
        String firstSql = "SELECT * FROM comment WHERE account_id = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(firstSql);
        preparedStatement.setString(1, reqId);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet firstResult = preparedStatement.executeQuery();
        List<CommentBean> comments = new ArrayList <>();

        while (firstResult.next()) {
            CommentBean comment = new CommentBean();
            comment.setMovieName(firstResult.getString("movie_name"));
            comment.setMovieScore(firstResult.getDouble("movie_score"));
            comment.setMovieComment(firstResult.getString("movie_comment"));
            comments.add(comment);
        }
        conn.commit();
        conn.close();
        return comments;
    }
}

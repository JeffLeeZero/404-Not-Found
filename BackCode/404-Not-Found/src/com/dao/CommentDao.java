package com.dao;

import com.DBUtil.DBUtil;
import com.bean.CommentBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    //查找评论
    public List<CommentBean> commentQuery(String movieId,int curPage,int pageSize) throws SQLException {
        String sql = "SELECT * FROM comment WHERE movie_id = ? ORDER BY submission_time DESC LIMIT ?,? ";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.valueOf(movieId));
        preparedStatement.setInt(2, curPage*pageSize);
        preparedStatement.setInt(3, pageSize);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        List<CommentBean> comments = new ArrayList <>();

        while (resultSet.next()) {
            CommentBean comment = new CommentBean();
            comment.setMovieId(String.valueOf(resultSet.getInt("movie_id")));
            comment.setNickname(resultSet.getString("account_nickname"));
            comment.setMovieScore(resultSet.getDouble("movie_score"));
            comment.setMovieComment(resultSet.getString("movie_comment"));
            comment.setSubmissionTime(String.valueOf(resultSet.getDate("submission_time")));
            comments.add(comment);
        }
        conn.commit();
        conn.close();
        return comments;
    }

    //查找评论数量
    public int commentNum(String movieId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM comment WHERE movie_id = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.valueOf(movieId));
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        int num = 0;

        if (resultSet.next()) {
            num = resultSet.getInt(1);
        }
        conn.commit();
        conn.close();
        return num;
    }

    //插入评论
    public boolean commentAdd(String reqId, String movieId, double movieScore, String movieComment) throws SQLException {
        String movieName = nameQuery(movieId,"movieId");
        String accountName = nameQuery(reqId,"accountId");
        String sql = "INSERT INTO comment (movie_id,movie_name,account_id,account_nickname,movie_score,movie_comment) " +
                "VALUES(?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.valueOf(movieId));
        preparedStatement.setString(2, movieName);
        preparedStatement.setInt(3, Integer.valueOf(reqId));
        preparedStatement.setString(4, accountName);
        preparedStatement.setDouble(5, movieScore);
        preparedStatement.setString(6, movieComment);
        preparedStatement.execute();
        conn.commit();
        conn.close();
        return true;
    }

    //查找用户名/电影名称
    public String nameQuery(String id, String type) throws SQLException {
        String dataTable = new String();
        String columName = new String();
        String resultName = new String();
        String name = new String();
        if(type == "accountId"){
            dataTable = "account";
            columName = "account_id";
            resultName = "account_nickname";
        }
        else if(type == "movieId"){
            dataTable = "movie";
            columName = "movie_id";
            resultName = "movie_name";
        }

        String sql = "SELECT * FROM " + dataTable + " WHERE " + columName + " = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.valueOf(id));
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            name = resultSet.getString(resultName);
        }
        conn.commit();
        conn.close();
        return  name;
    }
}

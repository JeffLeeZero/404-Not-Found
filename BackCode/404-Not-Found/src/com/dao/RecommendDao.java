package com.dao;

import com.DBUtil.DBUtil;
import com.bean.RecommendBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecommendDao {
    //推荐电影
    public List<RecommendBean> recommendQuery(String reqId,int constellationNum, int nums[]) throws SQLException {
        String firstSql = "SELECT * FROM movie WHERE movie_id = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(firstSql);
        preparedStatement.setInt(1, constellationNum);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet firstResult = preparedStatement.executeQuery();
        List<RecommendBean> recommendList = new ArrayList<>();

        if (firstResult.next()) {
            RecommendBean recommend = new RecommendBean();
            recommend.setMovieId(firstResult.getString("movie_id"));
            recommend.setMovieName(firstResult.getString("movie_name"));
            recommend.setMovieScore(firstResult.getDouble("movie_score"));
            recommend.setMovieSlogan(firstResult.getString("movie_slogan"));
            recommend.setMoviePoster(firstResult.getString("movie_poster"));
            recommend.setMovieBackground(firstResult.getString("movie_background"));
            recommendList.add(recommend);
        }

        conn.commit();
        conn.close();
        int[] recommendNum = nums;
        for(int i = 0; i<5; i++){
            RecommendBean recommend = movieQuery(recommendNum[i]);
            recommendList.add(recommend);
        }
        return recommendList;
    }

    //查找电影
    public RecommendBean movieQuery(int movieId) throws SQLException {
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, movieId);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet firstResult = preparedStatement.executeQuery();
        RecommendBean recommend = new RecommendBean();

        if (firstResult.next()) {
            recommend.setMovieId(firstResult.getString("movie_id"));
            recommend.setMovieName(firstResult.getString("movie_name"));
            recommend.setMovieScore(firstResult.getDouble("movie_score"));
            recommend.setMovieSlogan(firstResult.getString("movie_slogan"));
            recommend.setMoviePoster(firstResult.getString("movie_poster"));
            recommend.setMovieBackground(firstResult.getString("movie_background"));

        }
        conn.commit();
        conn.close();
        return  recommend;
    }


}

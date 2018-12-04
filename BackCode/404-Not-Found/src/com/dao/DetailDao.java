package com.dao;

import com.DBUtil.DBUtil;
import com.bean.DetailBean;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DetailDao {
    //查找详情
    public DetailBean detailQuery(String movieId) throws SQLException {
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.valueOf(movieId));
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        DetailBean detail = new DetailBean();

        if (resultSet.next()) {
            detail.setMovieId(movieId);
            detail.setMovieName(resultSet.getString("movie_name"));
            detail.setMovieScore(resultSet.getDouble("movie_score"));
            detail.setMovieIntro(resultSet.getString("movie_intro"));
            detail.setMoviePic1(resultSet.getString("movie_pic1"));
            detail.setMoviePic2(resultSet.getString("movie_pic2"));
            detail.setMoviePic3(resultSet.getString("movie_pic3"));
            detail.setMovieBackground(resultSet.getString("movie_background"));
        }
        conn.commit();
        conn.close();
        return detail;
    }

    //插入详情
    public void detailAdd() {
        try {
            File file = new File("/404-Not-Found/BackCode/404-Not-Found/MovieData");
            String [] fileName = file.list();
            int num = 13;
            for(String str:fileName){
                addToDB(str,num);
                num++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean addToDB(String str, int num) throws IOException, SQLException {
        //读取配置文件
        Properties property = new Properties();
        InputStream in = null;
        in = new FileInputStream("/404-Not-Found/BackCode/404-Not-Found/MovieData/"+str+"/"+num+".properties");
        property.load(new InputStreamReader(in,"utf-8"));
        String movieId = property.getProperty("movie_id");
        String movieName = property.getProperty("movie_name");
        double movieScore = Double.valueOf(property.getProperty("movie_score"));
        String movieIntro = property.getProperty("movie_intro");
        String movieSlogan = property.getProperty("movie_slogan");
        String sql = "INSERT INTO movie (movie_id,movie_name,movie_score,movie_intro,movie_slogan," +
                "movie_poster,movie_pic1,movie_pic2,movie_pic3,movie_background) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.valueOf(movieId));
        preparedStatement.setString(2, movieName);
        preparedStatement.setDouble(3, movieScore);
        preparedStatement.setString(4, movieIntro);
        preparedStatement.setString(5, movieSlogan);
        preparedStatement.setString(6, "../../BackCode/404-Not-Found/MovieData/"+str+"/movie_poster");
        preparedStatement.setString(7, "../../BackCode/404-Not-Found/MovieData/"+str+"/movie_pic1");
        preparedStatement.setString(8, "../../BackCode/404-Not-Found/MovieData/"+str+"/movie_pic2");
        preparedStatement.setString(9, "../../BackCode/404-Not-Found/MovieData/"+str+"/movie_pic3");
        preparedStatement.setString(10, "../../BackCode/404-Not-Found/MovieData/"+str+"/movie_background");
        preparedStatement.execute();
        conn.commit();
        conn.close();
        return true;
    }
}

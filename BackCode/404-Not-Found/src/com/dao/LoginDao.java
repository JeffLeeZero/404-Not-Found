package com.dao;

import com.DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    //查找账号
    public boolean loginCheck(String nickname, String password) throws SQLException{
        String sql = "SELECT * FROM account WHERE account_nickname = ? and account_password = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nickname);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            conn.commit();
            conn.close();
            return true;
        }
        conn.commit();
        conn.close();
        return false;
    }

    //获得昵称
    public String getNickname(String telNum) throws SQLException {
        String sql = "SELECT * FROM account WHERE tel_num = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, telNum);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String nickname = resultSet.getString("account_nickname");
            conn.commit();
            conn.close();
            return nickname;
        }
        conn.commit();
        conn.close();
        return null;
    }

    //获得id
    public String getAccountId(String nickname) throws SQLException {
        String sql = "SELECT * FROM account WHERE account_nickname = ?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nickname);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String accountId = resultSet.getString("account_id");
            conn.commit();
            conn.close();
            return accountId;
        }
        conn.commit();
        conn.close();
        return null;
    }
}

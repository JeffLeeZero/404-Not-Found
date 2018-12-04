package com.dao;

import com.DBUtil.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpDao {
    //增加数据
    public int signUp(String nickname, String password) throws SQLException {
        boolean notExisted = signUpCheck(nickname);
        if(notExisted == true){
            String sql = "INSERT INTO account (account_nickname,account_password) VALUES(?,?)";
            Connection conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            conn.commit();

            String getId = "SELECT * FROM account WHERE account_nickname =?";
            PreparedStatement getIdStatement = conn.prepareStatement(getId);
            getIdStatement.setString(1, nickname);
            getIdStatement.execute();
            //执行查询语句并返回结果集
            ResultSet resultSet = getIdStatement.executeQuery();
            resultSet.next();
            int accountId = resultSet.getInt("account_id");
            conn.commit();
            conn.close();
            return accountId;
        }
        return 0;
    }

    //查找账号
    public boolean signUpCheck(String nickname) throws SQLException{
        String sql = "SELECT * FROM account WHERE account_nickname =?";
        Connection conn = DBUtil.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nickname);
        preparedStatement.execute();
        //执行查询语句并返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            conn.commit();
            conn.close();
            return false;
        }
        conn.commit();
        conn.close();
        return true;
    }
}

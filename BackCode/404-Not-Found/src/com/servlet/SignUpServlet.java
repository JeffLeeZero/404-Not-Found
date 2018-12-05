package com.servlet;

import com.bean.LoginBean;
import com.bean.RequestBean;
import com.bean.ResponseBean;
import com.dao.SignUpDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        BufferedReader reader = request.getReader();
        String str = reader.readLine();

        //处理传入对象
        Gson gson = new Gson();
        Type requestType = new TypeToken<RequestBean<LoginBean>>(){}.getType();
        RequestBean<LoginBean> loginRequest = gson.fromJson(str,requestType);
        LoginBean account = loginRequest.getReqParam();

        //判断
        SignUpDao sud = new SignUpDao();
        ResponseBean<LoginBean> loginResponse = new ResponseBean <>();
        loginResponse.setResData(account);
        String message = null;
        boolean isSuccess = false;

        try{
            String accountId = sud.signUp(account.getNickname(),account.getPassword(),null);
            loginResponse.setReqId(accountId);
            if(accountId != null){
                message = "注册成功";
                isSuccess = true;
            }
            else{
                message = "用户已存在";
            }
            loginResponse.setMessage(message);
            loginResponse.setSuccess(isSuccess);
            String outputString = gson.toJson(loginResponse);
            out.print(outputString);
        }catch(Exception e){
            out.print(e.toString());
        }
        out.flush();
        out.close();
    }
}

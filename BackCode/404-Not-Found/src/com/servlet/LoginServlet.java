package com.servlet;

import com.bean.LoginBean;
import com.bean.RequestBean;
import com.bean.ResponseBean;
import com.dao.LoginDao;
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

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
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

        try{
            LoginDao ld = new LoginDao();
            ResponseBean<LoginBean> loginResponse = new ResponseBean <>();
            String accountId = ld.getAccountId(account.getNickname());
            loginResponse.setReqId(accountId);
            loginResponse.setResData(account);
            String message = null;
            boolean isSuccess;
            isSuccess = ld.loginCheck(account.getNickname(),account.getPassword());
            if(isSuccess == true){
                message = "登陆成功";
            }
            else{
                message = "用户名与密码不匹配";
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

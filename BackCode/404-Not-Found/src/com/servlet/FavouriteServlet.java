package com.servlet;

import com.bean.DetailBean;
import com.bean.LoginBean;
import com.bean.RequestBean;
import com.bean.ResponseBean;
import com.dao.DetailDao;
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

@WebServlet(name = "FavouriteServlet")
public class FavouriteServlet extends HttpServlet {
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
        Type requestType = new TypeToken<RequestBean<DetailBean>>(){}.getType();
        RequestBean<DetailBean> detailRequest = gson.fromJson(str,requestType);
        DetailBean detail = detailRequest.getReqParam();

        //判断

        try{
            DetailDao dd = new DetailDao();
            ResponseBean<LoginBean> loginResponse = new ResponseBean <>();
            loginResponse.setReqId(detailRequest.getReqId());
            loginResponse.setResData(null);
            String message = null;
            boolean isSuccess;
            isSuccess = dd.setFavourite(detailRequest.getReqId(),detail.getMovieId(),detail.isFavourite());
            if(isSuccess == true){
                message = "设置成功";
            }
            else{
                message = "设置失败";
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

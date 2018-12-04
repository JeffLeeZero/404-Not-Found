package com.servlet;

import com.bean.DetailBean;
import com.bean.MovieReqBean;
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

@WebServlet(name = "DetailServlet")
public class DetailServlet extends HttpServlet {
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
        Type requestType = new TypeToken<RequestBean<MovieReqBean>>(){}.getType();
        RequestBean<MovieReqBean> detailRequest = gson.fromJson(str,requestType);

        MovieReqBean reqParam = detailRequest.getReqParam();
        ResponseBean<DetailBean> detailResponse = new ResponseBean <>();
        detailResponse.setReqId(detailRequest.getReqId());

        //判断
        try{
            DetailDao dd = new DetailDao();
            DetailBean detail = dd.detailQuery(reqParam.getMovieId());
            String message = new String();
            boolean isSuccess;
            if(detail != null){
                message = "详情获取成功";
                isSuccess = true;
            }
            else{
                message = "详情为空";
                isSuccess = false;
            }
            detailResponse.setResData(detail);
            detailResponse.setMessage(message);
            detailResponse.setSuccess(isSuccess);
            String outputString = gson.toJson(detailResponse);
            out.print(outputString);
        }catch(Exception e){
            out.print(e.toString());
        }
        out.flush();
        out.close();
    }
}

package com.servlet;

import com.bean.CommentBean;
import com.bean.ReqPageInfo;
import com.bean.RequestBean;
import com.bean.ResponseBean;
import com.dao.CommentDao;
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
import java.util.List;

@WebServlet(name = "GetCommentServlet")
public class GetCommentServlet extends HttpServlet {
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
        Type requestType = new TypeToken<RequestBean<CommentBean>>(){}.getType();
        RequestBean<CommentBean> getCommentRequest = gson.fromJson(str,requestType);

        CommentBean reqParam = getCommentRequest.getReqParam();
        ReqPageInfo reqPageInfo = getCommentRequest.getReqPageInfo();
        ResponseBean<List<CommentBean>> getCommentResponse = new ResponseBean <>();
        getCommentResponse.setReqId(getCommentRequest.getReqId());
        int curPage = Integer.parseInt(reqPageInfo.getCurPage());
        int pageSize = Integer.parseInt(reqPageInfo.getPageSize());

        //判断
        try{
            CommentDao cd = new CommentDao();
            List<CommentBean> comments = cd.commentQuery(reqParam.getMovieId(),curPage,pageSize);
            String message = new String();
            boolean isSuccess;
            if(comments != null){
                message = String.valueOf(cd.commentNum(reqParam.getMovieId()));
                isSuccess = true;
            }
            else{
                message = "0";
                isSuccess = false;
            }
            getCommentResponse.setResData(comments);
            getCommentResponse.setMessage(message);
            getCommentResponse.setSuccess(isSuccess);
            String outputString = gson.toJson(getCommentResponse);
            out.print(outputString);
        }catch(Exception e){
            out.print(e.toString());
        }
        out.flush();
        out.close();
    }
}

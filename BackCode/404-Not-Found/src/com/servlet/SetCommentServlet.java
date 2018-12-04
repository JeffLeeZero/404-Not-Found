package com.servlet;

import com.bean.CommentBean;
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

@WebServlet(name = "SetCommentServlet")
public class SetCommentServlet extends HttpServlet {
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
        RequestBean<CommentBean> setCommentRequest = gson.fromJson(str,requestType);

        CommentBean reqParam = setCommentRequest.getReqParam();

        ResponseBean<CommentBean> setCommentResponse = new ResponseBean <>();
        setCommentResponse.setReqId(setCommentRequest.getReqId());
        CommentBean resData = new CommentBean();
        resData.setMovieId(reqParam.getMovieId());
        resData.setMovieScore(reqParam.getMovieScore());
        resData.setMovieComment(reqParam.getMovieComment());

        //判断
        try{
            CommentDao cd = new CommentDao();
            boolean isSuccess;
            if(reqParam.getMovieComment() == null){
                isSuccess = false;
            }
            else{
                isSuccess = cd.commentAdd(setCommentRequest.getReqId(),reqParam.getMovieId(),
                        reqParam.getMovieScore(),reqParam.getMovieComment());
            }
            String message = new String();
            if(isSuccess == true){
                message = "发表获取成功";
            }
            else{
                message = "发表评论失败";
            }
            String nickname = cd.nameQuery(setCommentRequest.getReqId(),"accountId");
            resData.setNickname(nickname);
            setCommentResponse.setResData(resData);
            setCommentResponse.setMessage(message);
            setCommentResponse.setSuccess(isSuccess);
            String outputString = gson.toJson(setCommentResponse);
            out.print(outputString);
        }catch(Exception e){
            out.print(e.toString());
        }
        out.flush();
        out.close();
    }
}

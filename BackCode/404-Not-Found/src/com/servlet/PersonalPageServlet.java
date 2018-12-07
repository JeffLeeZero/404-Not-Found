package com.servlet;

import com.bean.*;
import com.dao.MySlopeOneRecommender;
import com.dao.PersonalPageDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

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

@WebServlet(name = "PersonalPageServlet")
public class PersonalPageServlet extends HttpServlet {
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

        ResponseBean<PersonalPage> personalPageResponse = new ResponseBean <>();
        personalPageResponse.setReqId(detailRequest.getReqId());

        String path = this.getServletContext().getRealPath("movie_preferences.txt");
        List<RecommendedItem> recommendation = null;
        MySlopeOneRecommender msor = new MySlopeOneRecommender();
        //拿到推荐的电影
        recommendation = msor.mySlopeOneRecommender(11,12,path);

        //判断
        try{
            PersonalPageDao ppd = new PersonalPageDao();
            String nickname = ppd.getNickname(detailRequest.getReqId());
            List<FavMovieBean> favMovie = ppd.favMovie(detailRequest.getReqId());
            List<CommentBean> commentedMovie = ppd.commentedMovie(detailRequest.getReqId());
            PersonalPage pp = new PersonalPage();
            pp.setNickname(nickname);
            pp.setFavMovie(favMovie);
            pp.setCommentedMovie(commentedMovie);
            personalPageResponse.setResData(pp);

            String message = new String();
            boolean isSuccess;
            if(pp != null){
                message = "获取成功";
                isSuccess = true;
            }
            else{
                message = "主页为空";
                isSuccess = false;
            }
            personalPageResponse.setMessage(message);
            personalPageResponse.setSuccess(isSuccess);
            String outputString = gson.toJson(personalPageResponse);
            out.print(outputString);
        }catch(Exception e){
            out.print(e.toString());
        }
        out.flush();
        out.close();
    }
}

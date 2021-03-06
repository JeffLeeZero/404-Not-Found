package com.servlet;

import com.bean.MovieReqBean;
import com.bean.RecommendBean;
import com.bean.RequestBean;
import com.bean.ResponseBean;
import com.dao.MySlopeOneRecommender;
import com.dao.RecommendDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

@WebServlet(name = "RecommendServlet")
public class RecommendServlet extends HttpServlet {
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
        RequestBean<MovieReqBean> recommendRequest = gson.fromJson(str,requestType);

        MovieReqBean reqParam = recommendRequest.getReqParam();
        ConstellationSet constellation = ConstellationSet.valueOf(reqParam.getConstellation());
        int constellationNum = constellation.ordinal() + 1;
        ResponseBean<List<RecommendBean>> recommendResponse = new ResponseBean <>();
        recommendResponse.setReqId(recommendRequest.getReqId());

        //推荐算法
        String path = this.getServletContext().getRealPath("movie_preferences.txt");
        List<RecommendedItem> recommendation = null;
        MySlopeOneRecommender msor = new MySlopeOneRecommender();
        //拿到推荐的电影
        recommendation = msor.mySlopeOneRecommender(11,12,path);
        int nums[] = new int[5];
        nums[0] = (int)recommendation.get(constellationNum % 12).getItemID();
        nums[1] = (int)recommendation.get((constellationNum + 2) % 12).getItemID();
        nums[2] = (int)recommendation.get((constellationNum + 4) % 12).getItemID();
        nums[3] = (int)recommendation.get((constellationNum + 8) % 12).getItemID();
        nums[4] = (int)recommendation.get((constellationNum + 11) % 12).getItemID();

        //判断
        try{
            RecommendDao rd = new RecommendDao();
            List<RecommendBean> recommendList = rd.recommendQuery(recommendRequest.getReqId(),constellationNum,nums);
            String message = new String();
            boolean isSuccess;
            if(recommendList.size() == 6){
                message = "推荐电影获取成功";
                isSuccess = true;
            }
            else{
                message = "推荐电影不全或为空";
                isSuccess = false;
            }
            recommendResponse.setResData(recommendList);
            recommendResponse.setMessage(message);
            recommendResponse.setSuccess(isSuccess);
            GsonBuilder gsonbuilder = new GsonBuilder();
            gsonbuilder.serializeNulls();
            Gson gsonOutput = gsonbuilder.create();
            String outputString = gsonOutput.toJson(recommendResponse);
            out.print(outputString);
        }catch(Exception e){
            out.print(e.toString());
        }
        out.flush();
        out.close();
    }

    public enum ConstellationSet {
        Aries, Taurus, Gemini, Cancer, Leo, Virgo, Libra, Scorpio, Sagittarius, Capricorn, Aquarius, Pisces;
    }
}

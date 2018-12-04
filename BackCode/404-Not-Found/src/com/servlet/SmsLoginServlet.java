package com.servlet;

import com.bean.LoginBean;
import com.bean.RequestBean;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

@WebServlet(name = "SmsLoginServlet")
public class SmsLoginServlet extends HttpServlet {
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

        // 短信应用SDK AppID
        int appid = 1400167297; // 1400开头

        // 短信应用SDK AppKey
        String appkey = "49be6b5a0075d6fcf2154b36ccfb45f1";

        // 需要发送短信的手机号码
        String[] phoneNumbers = {loginRequest.getReqId()};

        // 短信模板ID，需要在短信应用中申请
        // NOTE: 这里的模板ID`7839`只是一个示例，
        // 真实的模板ID需要在短信控制台中申请
        int templateId = 240296;

        // 签名
        // NOTE: 这里的签名"腾讯云"只是一个示例，
        // 真实的签名需要在短信控制台中申请，另外
        // 签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "观者心剧中人";

        // 指定模板ID单发短信
        try {
            String[] params = {"233333","1"};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        } catch (com.github.qcloudsms.httpclient.HTTPException e) {
            e.printStackTrace();
        }
    }
}

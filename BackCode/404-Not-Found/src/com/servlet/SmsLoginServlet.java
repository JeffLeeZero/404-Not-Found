package com.servlet;

import com.bean.LoginBean;
import com.bean.RequestBean;
import com.bean.ResponseBean;
import com.bean.VerificationCode;
import com.dao.LoginDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.Random;

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

        //生成验证码
        Random random = new Random();
        String verificationCode = String.valueOf(random.nextInt(9000)+1000);

        // 短信应用SDK AppID
        int appid = 1400167297; // 1400开头

        // 短信应用SDK AppKey
        String appkey = "49be6b5a0075d6fcf2154b36ccfb45f1";

        // 需要发送短信的手机号码
        String[] phoneNumbers = {account.getTelNum()};

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

        try{
            /*String[] params = {verificationCode,"1"};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);*/

            LoginDao ld = new LoginDao();
            ResponseBean<VerificationCode> loginResponse = new ResponseBean <>();
            String message = ld.getNickname(account.getTelNum());
            String accountId;
            VerificationCode resData = new VerificationCode();
            if(message == null){
                accountId = null;
            }
            else{
                accountId = ld.getAccountId(message);
            }
            loginResponse.setReqId(accountId);
            resData.setVerificationCode(verificationCode);
            loginResponse.setResData(resData);

            boolean isSuccess = false;
            /*if(result.result == 0){
                isSuccess = true;
            }
            else{
                isSuccess = false;
            }*/

            loginResponse.setMessage(message);
            loginResponse.setSuccess(isSuccess);
            GsonBuilder gsonbuilder = new GsonBuilder();
            gsonbuilder.serializeNulls();
            Gson gsonOutput = gsonbuilder.create();
            String outputString = gsonOutput.toJson(loginResponse);
            out.print(outputString);
        }catch(Exception e){
            out.print(e.toString());
        }
        out.flush();
        out.close();
    }
}

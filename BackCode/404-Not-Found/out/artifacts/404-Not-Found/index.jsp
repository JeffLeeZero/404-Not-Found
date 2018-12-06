<!--
<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/11/17
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$
  </body>
</html>

-->

<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <base href="<%=basePath%>">

  <title>My JSF 'ajaxDemo.jsp' starting page</title>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->
  <script type="text/javascript" src="jquery-3.3.1.min.js"></script>
  <script>
      function ajaxFun() {
          $.ajax({
              type: "POST",
              url:"NicknameServlet",
              dataType: "json",
              data: JSON.stringify(NicknameData()),
              success: function (res) {
                  console.log("success" + res);
              },
              error: function (err) {
                  console.log("fail" + err);
              }

          })
      }

      function SmsLoginData() {
          var json = {
              "reqId": "",
              "reqParam": {
                  "telNum": "13247179966",
              }
          };
          return json;
      }

      function NicknameData() {
          var json = {
              "reqId": "",
              "reqParam": {
                  "nickname": "hhh",
                  "telNum": "13247179966",
              }
          };
          return json;
      }

      function DetailData() {
          var json = {
              "reqId": "1",
              "reqParam": {
                  "movieId": "1"
              }
          };
          return json;
      }

      function RecommendData() {
          var json = {
              "reqId": "1",
              "reqParam": {
                  "constellation": "Aries"
              }
          };
          return json;
      }

      function GetCommentData() {
          var json = {
              "reqId": "2",
              "reqParam": {
                  "movieId": "1"
              },
              "reqPageInfo": {
                  "curPage": "0",
                  "pageSize": "10"
              }
          };
          return json;
      }

      function SetCommentData() {
          var json = {
              "reqId": "2",
              "reqParam": {
                  "movieId": "1",
                  "movieScore": "10",
                  "movieComment": "test"
              }
          };
          return json;
      }
  </script>
</head>

<body>
<form id="form">
  UserName:<input type="text" name="username" id="username">
  PassWord:<input type="password" name="password" id="password">
  <button type="button" onclick="ajaxFun()">AJAX功能</button>
</form>
</body>
</html>


#404-Not-Found说明文档

>1. 主题：电影推荐
>2. 队伍名称：404-Not-Found

##操作说明

1. 导入数据库

   - 将\BackCode\zerocup.sql添加到MySQL中，请将数据库名命名为zerocup。

2. 修改配置文件

   - 将\404-Not-Found\com\DBUtil\db.properties中的数据库信息改为本机数据库信息（用户名、密码等）。

3. 运行项目

   - 以\BackCode\404-Not-Found为路径导入项目到idea中，部署tomcat运行。
   - 将\404-Not-Found文件夹复制到Tomcat安装路径下的webapps文件夹中，运行Tomcat。
   - 打开\FrontCode\404 Not Found\index.html即可。
   - 请使用用户名test，密码为111登陆或自创账户登陆。

4. 注意事项

   - 若要通过idea/eclipse导入项目后在项目中配置Tomcat，则需修改\BackCode\404-Not-Found\src\com\DBUtil\db.properties，且通过ide运行时会弹出404-Not-Found界面，此时直接打开\FrontCode\404 Not Found\index.html即可。

##设计理念
见设计文档

##开发技术
见开发文档

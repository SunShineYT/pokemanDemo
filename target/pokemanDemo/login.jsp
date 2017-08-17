<%--
  Created by IntelliJ IDEA.
  User: zhm
  Date: 2016/1/5
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    <h1>测试发送请求</h1>

    <form name="form" action="/controller/testAction/viewAll" method="post">
        用户名：<input type="text" name="name"><br/>
        密码： <input type="text" name="pwd"><br/>
        <input type="submit" value="登录">
    </form>
</div>

</body>
</html>

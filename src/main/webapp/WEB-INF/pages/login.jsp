<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dpcraft
  Date: 30/12/2018
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/css/regcss.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        function validate() {
            console.log("call validate")
            if(document.forms.myform.username.value == "") {
                alert("用户名不能为空");
                document.forms.myform.username.focus();
                return false;
            }else if(document.forms.myform.password.value == "") {
                alert("密码不能为空");
                document.forms.myform.password.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div id="content">
    <div id="form">
        <h1>用户登录</h1></br>
        <form action="login.action" method="post" id="myform" onsubmit="return validate()">
            用户名<input type="text" id="username" name="username" style="width:190px; height:26px;margin-left:39px;"></br>
            密码<input type="password" id="password" name="password" style="width:190px; height:26px; margin-top: 8px; margin-left:54px"/></br>
            <input type="submit" value="登录" style="width:50px; height:30px; margin-top: 8px;">
            <a href="registerPage.action">注册</a>

        </form>
        <c:if test="${errorMsg != null}">
            <span style="color:red">${errorMsg}</span>
        </c:if>
        <c:if test="${noticeMsg != null}">
            <span style="color:green">${noticeMsg}</span>
        </c:if>
    </div>
</div>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/css/authent.css"/>" rel="stylesheet" type="text/css"/>
<html>
<head>
    <title>Аутентификация</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>
<body>
<div class="top">
    <span class="right">
        <a href="${app}">
            <strong>Вернуться назад</strong>
        </a>
    </span>
    <div class="clr"></div>
</div>

<div id="wrapper">
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <div class="message"><c:out value="${sessionScope.message}" /></div>
    <form name="login-form" class="login-form" method="post" action="${app}/login">
        <div class="header">
            <h1>Аутентификация</h1>
            <span>Введите ваши регистрационные данные для входа.</span>
        </div>

        <div class="content">
            <input name="login" type="text" class="input username" value="admin" onfocus="this.value=''"/>
            <input name="password" type="password" class="input password" value="admin" onfocus="this.value=''"/>
        </div>

        <div class="footer">
            <button type="submit" name="submit" value="enter">ВОЙТИ</button>

            <form>
                <button formaction="${app}/pages/registration.jsp">Регистрация</button>
            </form>
            <%--<button type="submit" name="submit" value="registration" >Регистрация</button>--%>
        </div>
    </form>

</div>
<div class="gradient"></div>

<script type="text/javascript">
    $(document).ready(function() {
        $(".username").focus(function() {
            $(".user-icon").css("left","-48px");
        });
        $(".username").blur(function() {
            $(".user-icon").css("left","0px");
        });

        $(".password").focus(function() {
            $(".pass-icon").css("left","-48px");
        });
        $(".password").blur(function() {
            $(".pass-icon").css("left","0px");
        });
    });
</script>

</body>
</html>

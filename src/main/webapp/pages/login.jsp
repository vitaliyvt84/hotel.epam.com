<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value="/css/loginForm.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : initParam['locale']}" scope="session" />
<fmt:setLocale value="${lang}" />
<%--<fmt:setBundle basename="messages" />--%>

<html>
<head>
    <title><fmt:message key="authentification" /></title>
</head>
<body>
<div class="top">
    <span class="right">
        <a href="${app}">
            <strong><fmt:message key="comeBack" /></strong>
        </a>
    </span>
    <div class="clr"></div>
</div>

<div id="wrapper">
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <div class="message">${loginMessage}</div>
    <form name="login-form" class="login-form" method="post" action="${app}/login">
        <div class="header">
            <h1><fmt:message key="authentification" /></h1>
            <span><fmt:message key="authentFormMessage" /></span>
        </div>

        <div class="content">
            <input name="login" type="text" class="input username" value="<fmt:message key="authLogin" />" onfocus="this.value=''"/>
            <input name="password" type="password" class="input password" value="<fmt:message key="password" />" onfocus="this.value=''"/>
        </div>

        <div class="footer">
            <button class="btn btn-success" type="submit" name="submit" value="enter"><fmt:message key="signIn" /></button>

            <form>
                <button class="btn btn-success" formaction="${app}/pages/registration.jsp"><fmt:message key="registration" /></button>
            </form>
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

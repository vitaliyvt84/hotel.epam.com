<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value="/css/mine.css"/>" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="messages" />

<form class="form-group">
    <select class="form-select" id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
        <%--<option value="uk" ${language == 'uk' ? 'selected' : ''}>Ukraine</option>--%>
    </select>
</form>
<div class="container-fluid text-center" style="background-color:#f49516;color:#fff;height:70px;">
    <div class="p-3">
        <h3>"Hotel rooms" Booking room system</h3>
    </div>
</div>

<nav class="fill">
    <ul>
        <li><a href="${app}"><fmt:message key="home" /></a></li>
        <li><a href="${app}/aboutUs"><fmt:message key="aboutUs" /></a></li>
        <li><a href="${app}/contactUs"><fmt:message key="contactUs" /></a></li>
        <li><a href="${app}/searchRoom"><fmt:message key="searchRoom" /></a></li>
        <c:if test="${sessionScope.user != null && sessionScope.user.roleId == 3}">
            <li><a href="${app}/personalCabinet?param=1"><fmt:message key="leavePreOrder" /></a></li>
            <li><a href="${app}/userOrders"><fmt:message key="orders" /></a></li>
        </c:if>
        <c:if test="${sessionScope.user != null && sessionScope.user.roleId == 2}">
            <li><a href="${app}/preOrders"><fmt:message key="preOrders" /></a></li>
        </c:if>
        <c:if test="${sessionScope.user != null && sessionScope.user.roleId == 1}">
            <li><a href="${app}/users">Пользователи</a></li>
            <li><a href="${app}/rooms">Номера</a></li>
            <li><a href="${app}/roomsClasses">Классы номеров</a></li>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <li><a href="${app}/personalCabinet?param=2"><fmt:message key="personalData" /></a></li>
            <li><a href="${app}/logout"><fmt:message key="logout" /></a></li>
        </c:if>
        <c:if test="${user == null}">
            <li><a href="${app}/login"><fmt:message key="login" /></a></li>
            <li><a href="${app}/registration"><fmt:message key="registration" /></a></li>
        </c:if>

    </ul>
</nav>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pages/common/menu.jspf" %>
<%@ taglib prefix="options" uri="http://com.hotel/options" %>

<html>
<head>
    <title><fmt:message key="pre-orders" /></title>
</head>
<body>
<h4><fmt:message key="requestsFromUsers" />: </h4>
<table class="table">
    <caption><fmt:message key="requestsFromUsers" /></caption>
    <thead class="thead-light">
    <tr class="table-primary">
        <th><fmt:message key="preOrderId" /></th>
        <th><fmt:message key="username" /></th>
        <th><fmt:message key="phone-short" /></th>
        <th>E-mail</th>
        <th><fmt:message key="apartmentClass" /></th>
        <th><fmt:message key="preOrderRegistrationTime" /></th>
        <th><fmt:message key="numberOfAdult" /></th>
        <th><fmt:message key="numberOfChild" /></th>
        <th><fmt:message key="numberOfRooms" /></th>
        <th><fmt:message key="arrivalDate" /></th>
        <th><fmt:message key="dateOfDeparture" /></th>
        <th><fmt:message key="orderStatus" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${preOrders}" var="preOrder">
        <tr>
            <td>${preOrder.id}</td>
            <c:forEach items="${users}" var="user">
                <c:if test="${user.id == preOrder.userId}">
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                    <td>${user.email}</td>
                </c:if>
            </c:forEach>
            <c:forEach items="${apartmentClasses}" var="apartmentClass">
                <c:if test="${apartmentClass.id == preOrder.apartmentClassId}">
                    <td>${apartmentClass.name}</td>
                </c:if>
            </c:forEach>
            <td>${preOrder.createTime}</td>
            <td>${preOrder.numberOfAdult}</td>
            <td>${preOrder.numberOfChild}</td>
            <td>${preOrder.numberOfRooms}</td>
            <td>${preOrder.checkIn}</td>
            <td>${preOrder.checkOut}</td>
            <td bgcolor="#adff2f"><options:ch_mn_st status="${preOrder.status}"/></td>
            <c:if test="${preOrder.status.value == 0}">
                <form class="pre_orders_list" name="pre_orders_list" method="post" action="${app}/preOrders">
                    <td><button class="btn btn-warning" type="submit" name="pre_order_id" value="${preOrder.id}"><fmt:message key="pickUp" /></button></td>
                </form>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="options" uri="http://com.hotel/options" %>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

<html>
<head>
    <title>Предзаказы</title>
</head>
<body>
<jsp:include page="/pages/common/menu.jsp"/>
<h4>Заявки от пользователей: </h4>
<table class="table">
    <caption>Заявки от пользователей</caption>
    <thead class="thead-light">
    <tr class="table-primary">
        <th>Id заявки</th>
        <th>Имя пользователя</th>
        <th>Телефон</th>
        <th>E-mail</th>
        <th>Класс апартаментов</th>
        <th>Время регистрации заявки</th>
        <th>Кол-во взрослых</th>
        <th>Кол-во детей</th>
        <th>Кол-во комнат</th>
        <th>Дата заезда</th>
        <th>Дата выезда</th>
        <th>Статус заказа</th>
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
                    <td><button class="btn btn-warning" type="submit" name="pre_order_id" value="${preOrder.id}">Подобрать</button></td>
                </form>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>


<jsp:include page="/pages/common/footer.jsp"/>
</body>
</html>

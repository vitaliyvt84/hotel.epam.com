<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="options" uri="http://com.hotel/options" %>
<link href="<c:url value="/css/create_form.css"/>" rel="stylesheet" type="text/css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
        crossorigin="anonymous"></script>

<html>
<head>
    <title>Мои заказы</title>
</head>
<%@ include file="/pages/common/menu.jspf" %>
<table class="table">
    <caption>Мои заказы</caption>
    <thead class="thead-light">
    <tr class="table-warning">
        <th>Номер заказа</th>
        <th>Фото</th>
        <th>Дата заезда</th>
        <th>Дата выезда</th>
        <th>Номер апартаментов</th>
        <th>Название апартаментов</th>
        <th>Класс апартаментов</th>
        <th>Кол-во посетителей</th>
        <th>Кол-во комнат</th>
        <th>Цена</th>
        <th>Статус заказа</th>
    </tr>
    </thead>
    <tbody>
    <form class="payment_room" name="payment_room" method="post" action="${app}/userOrders">
    <c:forEach items="${bookingList}" var="booking">
        <tr>
            <td>${booking.id}</td>
            <td>
                <div id="carouselControls${booking.apartmentId}" data-interval="false" class="carousel slide">
                    <div class="carousel-inner">
                        <c:forEach items="${apartmentImageList}" var="apartmentImage">
                            <c:if test="${apartmentImage.apartmentId == booking.apartmentId}">
                                <c:choose>
                                    <c:when test="${apartmentImage.imageType == 1}">
                                        <div class="carousel-item active">
                                            <img src="${app}/images/rooms/${apartmentImage.imageURL}" class="d-block w-100" alt="${apartmentImage.id}">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="carousel-item">
                                            <img src="${app}/images/rooms/${apartmentImage.imageURL}" class="d-block w-100" alt="${apartmentImage.id}">
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselControls${booking.apartmentId}"  data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Предыдущий</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselControls${booking.apartmentId}"  data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Следующий</span>
                    </button>
                </div>
            </td>
            <td><fmt:formatDate value="${booking.dateIn}" pattern="yyyy-MM-dd" /></td>
            <td><fmt:formatDate value="${booking.dateOut}" pattern="yyyy-MM-dd" /></td>
            <c:forEach items="${apartments}" var="apartment">
                <c:if test="${apartment.id == booking.apartmentId}">
                    <td>${apartment.number}</td>
                    <td>${apartment.name}</td>
                    <c:set var="apart_class_id" value="${apartment.apartmentClassId}"/>
                </c:if>
            </c:forEach>
            <c:forEach items="${apartmentClassList}" var="apartmentClass">
                <c:if test="${apartmentClass.id == apart_class_id}">
                    <td>${apartmentClass.name}</td>
                </c:if>
            </c:forEach>
            <td>
                    ${booking.numberOfAdult} взрослых
                    ${booking.numberOfChild} детей
            </td>
            <td>${booking.numberOfRooms}</td>
            <td>${booking.price}</td>
            <c:if test="${booking.status.value == 1}">
                <td bgcolor="#adff2f">Забронирован</td>

                    <td><button class="btn btn-success" type="submit" name="booking_id" value="${booking.id}">Оплатить</button></td>
                    <td><button class="btn btn-warning" type="submit" name="cancel_booking_id" value="${booking.id}">Отменить</button></td>

            </c:if>
            <c:if test="${booking.status.value == 2}">
                <td bgcolor="#adff2f">Оплачен</td>
            </c:if>
        </tr>

    </c:forEach>
    </form>
    </tbody>
</table>


</br>
</br>

<table class="table">
    <caption>Мои пред заказы</caption>
    <thead class="thead-light">
    <tr class="table-warning">
        <th>ID</th>
        <th>Время создания</th>
        <th>Дата заезда</th>
        <th>Дата выезда</th>
        <th>Класс апартаментов</th>
        <th>Кол-во взрослых</th>
        <th>Кол-во детей</th>
        <th>Кол-во комнат</th>
        <th>Статус заказа</th>
    </tr>
    </thead>
    <tbody>
    <form class="review_room" name="review_room" method="post" action="${app}/userOrders">
    <c:forEach items="${preOrderList}" var="preOrder">
        <tr>
            <td>${preOrder.id}</td>
            <td>${preOrder.createTime}</td>
            <td><fmt:formatDate value="${preOrder.checkIn}" pattern="yyyy-MM-dd" /></td>
            <td><fmt:formatDate value="${preOrder.checkOut}" pattern="yyyy-MM-dd" /></td>
            <c:forEach items="${apartmentClassList}" var="apartmentClass">
                <c:if test="${apartmentClass.id == preOrder.apartmentClassId}">
                    <td>${apartmentClass.name}</td>
                </c:if>
            </c:forEach>
            <td>${preOrder.numberOfAdult}</td>
            <td>${preOrder.numberOfChild}</td>
            <td>${preOrder.numberOfRooms}</td>

            <td bgcolor="#adff2f"><options:ch_us_st status="${preOrder.status.value}"/></td>

            <c:if test="${preOrder.status.value == 1}">
                <td><button class="btn btn-success" type="submit" name="pre_order_id" value="${preOrder.id}">Просмотреть</button>${preOrder.id}</td>
            </c:if>
        </tr>
    </c:forEach>
    </form>
    </tbody>
</table>
<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>

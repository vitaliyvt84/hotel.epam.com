<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pages/common/menu.jspf" %>
<%@ taglib prefix="options" uri="http://com.hotel/options" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
        crossorigin="anonymous"></script>

<html>
<head>
    <title><fmt:message key="myOrders" /></title>
</head>

<table class="table">
    <caption><fmt:message key="myOrders" /></caption>
    <thead class="thead-light">
    <tr class="table-warning">
        <th><fmt:message key="orderNumber" /></th>
        <th><fmt:message key="photo" /></th>
        <th><fmt:message key="arrivalDate" /></th>
        <th><fmt:message key="dateOfDeparture" /></th>
        <th><fmt:message key="apartmentNumber" /></th>
        <th><fmt:message key="apartmentName" /></th>
        <th><fmt:message key="apartmentClass" /></th>
        <th><fmt:message key="numberOfVisitors" /></th>
        <th><fmt:message key="numberOfRooms" /></th>
        <th><fmt:message key="price" /></th>
        <th><fmt:message key="orderStatus" /></th>
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
                        <span class="visually-hidden"><fmt:message key="previous" /></span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselControls${booking.apartmentId}"  data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden"><fmt:message key="next" /></span>
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
                    ${booking.numberOfAdult} <fmt:message key="adults" />
                    ${booking.numberOfChild} <fmt:message key="children" />
            </td>
            <td>${booking.numberOfRooms}</td>
            <td>${booking.price}</td>
            <c:if test="${booking.status.value == 1}">
                <td bgcolor="#adff2f"><fmt:message key="booked" /></td>

                    <td><button class="btn btn-success" type="submit" name="booking_id" value="${booking.id}"><fmt:message key="pay" /></button></td>
                    <td><button class="btn btn-warning" type="submit" name="cancel_booking_id" value="${booking.id}"><fmt:message key="cancel" /></button></td>

            </c:if>
            <c:if test="${booking.status.value == 2}">
                <td bgcolor="#adff2f"><fmt:message key="paidUp" /></td>
            </c:if>
        </tr>

    </c:forEach>
    </form>
    </tbody>
</table>


</br>
</br>

<table class="table">
    <caption><fmt:message key="myPreOrders" /></caption>
    <thead class="thead-light">
    <tr class="table-warning">
        <th>ID</th>
        <th><fmt:message key="createTime" /></th>
        <th><fmt:message key="arrivalDate" /></th>
        <th><fmt:message key="dateOfDeparture" /></th>
        <th><fmt:message key="apartmentClass" /></th>
        <th><fmt:message key="numberOfAdult" /></th>
        <th><fmt:message key="numberOfChild" /></th>
        <th><fmt:message key="numberOfRooms" /></th>
        <th><fmt:message key="orderStatus" /></th>
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
                <td><button class="btn btn-success" type="submit" name="pre_order_id" value="${preOrder.id}"><fmt:message key="view" /></button>${preOrder.id}</td>
            </c:if>
        </tr>
    </c:forEach>
    </form>
    </tbody>
</table>
<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>
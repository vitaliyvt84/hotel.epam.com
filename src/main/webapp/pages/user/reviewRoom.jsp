<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value="/css/create_form.css"/>" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
        crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

<html>
<head>
    <title>Обзор комнаты</title>
</head>
<body>
<%@ include file="/pages/common/menu.jspf" %>

<div class="row border justify-content-md-center">
    <div class="col-md-8 p-4 m-">
        <div id="carouselControls${apartment.id}" class="carousel slide" data-interval="false">
            <div class="carousel-inner">
                <c:forEach items="${apartmentImages}" var="apartmentImage">
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
                </c:forEach>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselControls${apartment.id}"  data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Предыдущий</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselControls${apartment.id}"  data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Следующий</span>
            </button>
        </div>
    </div>
</div>

<br/>
<h6>Пожалуйста проверте все данные и нажмите 'Забронировать' или нажмите 'Отменить'!</h6>
<h6>Ваш заказ:</h6>
<br/>
<div class="card">
    <div class="card-body">
        <h5 class="card-title">${apartment.name}</h5>
        <h6 class="card-subtitle mb-2 text-muted">Номер апартаментов:</h6>
        <p class="card-text">${apartment.number}</p>
        <h6 class="card-subtitle mb-2 text-muted">Класс апартаментов:</h6>
        <p class="card-text">${apartmentClass.name}</p>
        <h6 class="card-subtitle mb-2 text-muted">Дата заезда:</h6>
        <p class="card-text"><fmt:formatDate value="${preOrderDTO.checkIn}" pattern="yyyy-MM-dd" /></p>
        <h6 class="card-subtitle mb-2 text-muted">Дата выезда:</h6>
        <p class="card-text"><fmt:formatDate value="${preOrderDTO.checkOut}" pattern="yyyy-MM-dd" /></p>
        <h6 class="card-subtitle mb-2 text-muted">Количество комнат:</h6>
        <p class="card-text">${apartment.countOfRoom}</p>
        <h6 class="card-subtitle mb-2 text-muted">Количество взрослых:</h6>
        <p class="card-text">${preOrderDTO.numberOfAdult}</p>
        <h6 class="card-subtitle mb-2 text-muted">Количество детей:</h6>
        <p class="card-text">${preOrderDTO.numberOfChild}</p>
        <h6 class="card-subtitle mb-2 text-muted">Имя и фамилия:</h6>
        <p class="card-text">${user.name}</p>
        <h6 class="card-subtitle mb-2 text-muted">Контактный телефон:</h6>
        <p class="card-text">${user.phone}</p>
        <h6 class="card-subtitle mb-2 text-muted">E-mail:</h6>
        <p class="card-text">${user.email}</p>
        <h6 class="card-subtitle mb-2 text-muted">Цена:</h6>
        <p class="card-text">${totalPrice}</p>

        <form class="order" name="order" method="post" action="${app}/reviewRoom">
            <button class="btn btn-success" type="submit" name="book_room" value="book">Забронировать комнату</button>
            <button class="btn btn-warning" type="submit" name="book_room" value="cancel">Отменить</button>
        </form>

    </div>
</div>


<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>

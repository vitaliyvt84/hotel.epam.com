<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pages/common/menu.jspf" %>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
        crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

<html>
<head>
    <title><fmt:message key="roomOverview" /></title>
</head>
<body>

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
                <span class="visually-hidden"><fmt:message key="previous" /></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselControls${apartment.id}"  data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden"><fmt:message key="next" /></span>
            </button>
        </div>
    </div>
</div>

<br/>
<h6><fmt:message key="reviewRoomMessage" />!</h6>
<h6><fmt:message key="yourOrder" />:</h6>
<br/>
<div class="card">
    <div class="card-body">
        <h5 class="card-title">${apartment.name}</h5>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="apartmentNumber" />:</h6>
        <p class="card-text">${apartment.number}</p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="apartmentClass" />:</h6>
        <p class="card-text">${apartmentClass.name}</p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="arrivalDate" />:</h6>
        <p class="card-text"><fmt:formatDate value="${preOrderDTO.checkIn}" pattern="yyyy-MM-dd" /></p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="dateOfDeparture" />:</h6>
        <p class="card-text"><fmt:formatDate value="${preOrderDTO.checkOut}" pattern="yyyy-MM-dd" /></p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="numberOfRooms" />:</h6>
        <p class="card-text">${apartment.countOfRoom}</p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="numberOfAdult" />:</h6>
        <p class="card-text">${preOrderDTO.numberOfAdult}</p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="numberOfChild" />:</h6>
        <p class="card-text">${preOrderDTO.numberOfChild}</p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="nameSurname" />:</h6>
        <p class="card-text">${user.name}</p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="contactPhone" />:</h6>
        <p class="card-text">${user.phone}</p>
        <h6 class="card-subtitle mb-2 text-muted">E-mail:</h6>
        <p class="card-text">${user.email}</p>
        <h6 class="card-subtitle mb-2 text-muted"><fmt:message key="totalPrice" />:</h6>
        <p class="card-text">${totalPrice}</p>

        <form class="order" name="order" method="post" action="${app}/reviewRoom">
            <button class="btn btn-success" type="submit" name="book_room" value="book"><fmt:message key="bookRoom" /></button>
            <button class="btn btn-warning" type="submit" name="book_room" value="cancel"><fmt:message key="cancel" /></button>
        </form>
    </div>
</div>

<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>
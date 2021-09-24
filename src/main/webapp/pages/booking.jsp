<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<link href="<c:url value="/css/create_form.css"/>" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/jquery-3.6.0.slim.min.js" integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
        crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">--%>

<html>
<head>
    <title>Booking</title>
</head>
<body>
<jsp:include page="/pages/common/menu.jsp"/>

<form class="collection-sort" name="sort_form" method="get" action="${app}/bookingRoom">
    <div class="input-group mb-3">
        <button class="btn btn-outline-primary" type="submit" name="button_sort" value="sortClick">Сортировать</button>
        <select class="form-select form-select-bg-green" id="sort-select" name="sort_by">
            <option value="APC_ID" ${opt_val eq "APC_ID" ? "selected" : ""}>Класс</option>
            <option value="PR" ${opt_val eq "PR" ? "selected" : ""}>Цена, Меньш - Больш</option>
            <option value="PRD" ${opt_val eq "PRD" ? "selected" : ""}>Цена, Больш - Меньш</option>
            <option value="MCA" ${opt_val eq "MCA" ? "selected" : ""}>Кол-во мест</option>
            <option value="NM" ${opt_val eq "NM" ? "selected" : ""}>А - Я</option>
            <option value="ST" ${opt_val eq "ST" ? "selected" : ""}>Статус</option>
        </select>
    </div>
</form>

<%--<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>Apartment ID</th>
        <th>Apartment Name</th>
        <th>Apartment Number</th>
        <th>Apartment Status</th>
        <th>Number of Adult</th>
        <th>Apartment Class</th>
        <th>Price</th>
    </tr>

    <c:forEach items="${apartmentList}" var="apartment">
        <tr>
            <td>${apartment.id}</td>
            <td>${apartment.name}</td>
            <td>${apartment.number}</td>
            <td>${apartmentBookingStatus[apartment.id]}</td>
            <td>${apartment.maxCountOfAdult}</td>
            <td>${apartment.apartmentClassId}</td>
            <td>${apartment.price}</td>

        </tr>
    </c:forEach>
</table>--%>
<nav aria-label="Apartment page navigation">
    <ul class="pagination">
        <c:choose>
            <c:when test="${currentPage == 1}">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="${app}/bookingRoom?page=${currentPage - 1}">Previous</a>
                </li>
            </c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active" aria-current="page">
                        <span class="page-link">${i}</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="${app}/bookingRoom?page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentPage >= noOfPages}">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="${app}/bookingRoom?page=${currentPage + 1}">Next</a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:if test="">

        </c:if>
    </ul>
</nav>







<c:if test="${preOrderDTO != null}">
    <div class="container p-3">
        <div class="row border">
            <h5>Параметры для поиска которые Вы задали:</h5>
        </div>
        <div class="row border">

            <div class="col-md-4 p-2">
                <div class="card">
                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-danger">Дата заезда:</h6>
                        <p class="card-text"><fmt:formatDate value="${preOrderDTO.checkIn}" pattern="yyyy-MM-dd" /></p>
                        <h6 class="card-subtitle mb-2 text-muted">Дата выезда:</h6>
                        <p class="card-text"><fmt:formatDate value="${preOrderDTO.checkOut}" pattern="yyyy-MM-dd" /></p>
                        <c:if test="${user != null && user.roleId == 2}">
                            <h6 class="card-subtitle mb-2 text-muted">Id пользователя:</h6>
                            <p class="card-text">${preOrderDTO.userId}</p>
                            <h6 class="card-subtitle mb-2 text-muted">Класс номера:</h6>
                            <c:forEach items="${apartmentClassList}" var="apartmentClass">
                                <c:if test="${apartmentClass.id == preOrderDTO.apartmentClassId}">
                                    <p class="card-text">${apartmentClass.name}</p>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-md-4 p-2">
                <div class="card">
                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-muted">Кол-во проживающих:</h6>
                        <p class="card-text">${preOrderDTO.numberOfAdult} взрослых</p>
                        <p class="card-text">${preOrderDTO.numberOfChild} детей</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 p-2">
                <div class="card">
                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-muted">Кол-во комнат:</h6>
                        <p class="card-text">${preOrderDTO.numberOfRooms}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:choose>
    <c:when test="${user != null && user.roleId == 2}">
        <form class="choose_room" name="choose_room" method="post" action="${app}/bookingRoom">
            <input type="hidden" name="choose_room" value="manager"/>

            <c:forEach items="${apartmentList}" var="apartment">
                <div class="container">
                    <div class="row bg-light border" >
                        <div class="col-md-4 p-md-4">

                            <div id="carouselControls${apartment.id}" class="carousel slide" data-interval="false">
                                <div class="carousel-inner">
                                    <c:forEach items="${apartmentImageList}" var="apartmentImage">
                                        <c:if test="${apartmentImage.apartmentId == apartment.id}">
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

                        <div class="col-md-4 p-3">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${apartment.name}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">Количество комнат:</h6>
                                    <p class="card-text">${apartment.countOfRoom}</p>
                                    <h6 class="card-subtitle mb-2 text-muted">Максимальное кол-во взрослых:</h6>
                                    <p class="card-text">${apartment.maxCountOfAdult}</p>
                                    <h6 class="card-subtitle mb-2 text-muted">Максимальное кол-во детей:</h6>
                                    <p class="card-text">${apartment.maxCountOfChild}</p>
                                    <h6 class="card-subtitle mb-2 text-muted">Цена(сутки/чел):</h6>
                                    <p class="card-text">${apartment.price}</p>
                                    <h6 class="card-subtitle mb-2 text-muted">Суммарная стоимость:</h6>
                                    <p class="card-text"><my:total_price num_ad="${preOrderDTO.numberOfAdult}" num_ch="${preOrderDTO.numberOfChild}"
                                                                         ap_price="${apartment.price}" num_day="${numberOfDays}"/></p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 p-3">
                            <div class="card">
                                <div class="card-body">
                                    <h6 class="card-subtitle mb-2 text-muted">Статус номера:</h6>
                                    <c:choose>
                                        <c:when test="${apartmentBookingStatus[apartment.id] eq 'EMPTY'}">
                                            <p class="card-text bg-success">${apartmentBookingStatus[apartment.id]}</p>
                                        </c:when>
                                        <c:otherwise>
                                            <p class="card-text bg-danger">${apartmentBookingStatus[apartment.id]}</p>
                                        </c:otherwise>
                                    </c:choose>

                                    <h6 class="card-subtitle mb-2 text-muted">Класс номера:</h6>
                                    <c:forEach items="${apartmentClassList}" var="apartmentClass">
                                        <c:if test="${apartment.apartmentClassId == apartmentClass.id}">
                                            <p class="card-text">${apartmentClass.name}</p>
                                        </c:if>
                                    </c:forEach>

                                    <c:if test="${apartmentBookingStatus[apartment.id].value eq 0}">
                                        <button class="btn btn-success" type="submit" name="manager_choose" value="${apartment.id}">Отправить клиенту</button>${apartment.id}
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </form>
    </c:when>

    <c:otherwise>
        <form class="choose_room" name="choose_room" method="post" action="${app}/bookingRoom">
            <input type="hidden" name="choose_room" value="user"/>

                <c:forEach items="${apartmentList}" var="apartment">
                    <div class="container">
                        <div class="row bg-light border" >
                            <div class="col-md-4 p-md-4">

                                <div id="carouselControls${apartment.id}" class="carousel slide" data-bs-ride="carousel">
                                    <div class="carousel-inner">
                                        <c:forEach items="${apartmentImageList}" var="apartmentImage">
                                            <c:if test="${apartmentImage.apartmentId == apartment.id}">
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

                            <div class="col-md-4 p-3">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title">${apartment.name}</h5>
                                        <h6 class="card-subtitle mb-2 text-muted">Количество комнат:</h6>
                                        <p class="card-text">${apartment.countOfRoom}</p>
                                        <h6 class="card-subtitle mb-2 text-muted">Максимальное кол-во взрослых:</h6>
                                        <p class="card-text">${apartment.maxCountOfAdult}</p>
                                        <h6 class="card-subtitle mb-2 text-muted">Максимальное кол-во детей:</h6>
                                        <p class="card-text">${apartment.maxCountOfChild}</p>
                                        <h6 class="card-subtitle mb-2 text-muted">Цена(сутки/чел):</h6>
                                        <p class="card-text">${apartment.price}</p>
                                        <h6 class="card-subtitle mb-2 text-muted">Суммарная стоимость:</h6>
                                        <p class="card-text"><my:total_price num_ad="${preOrderDTO.numberOfAdult}" num_ch="${preOrderDTO.numberOfChild}"
                                                                             ap_price="${apartment.price}" num_day="${numberOfDays}"/></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 p-3">
                                <div class="card">
                                    <div class="card-body">
                                        <h6 class="card-subtitle mb-2 text-muted">Статус номера:</h6>
                                        <c:choose>
                                            <c:when test="${apartmentBookingStatus[apartment.id] eq 'EMPTY'}">
                                                <p class="card-text bg-success">${apartmentBookingStatus[apartment.id]}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="card-text bg-danger">${apartmentBookingStatus[apartment.id]}</p>
                                            </c:otherwise>
                                        </c:choose>

                                        <h6 class="card-subtitle mb-2 text-muted">Класс номера:</h6>
                                        <c:forEach items="${apartmentClassList}" var="apartmentClass">
                                            <c:if test="${apartment.apartmentClassId == apartmentClass.id}">
                                                <p class="card-text">${apartmentClass.name}</p>
                                            </c:if>
                                        </c:forEach>

                                        <c:if test="${apartmentBookingStatus[apartment.id].value eq 0}">
                                            <button class="btn btn-success" type="submit" name="user_choose" value="${apartment.id}">Посмотреть детальную информацию</button>${apartment.id}
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
        </form>
    </c:otherwise>
</c:choose>


<jsp:include page="/pages/common/footer.jsp"/>
</body>
</html>

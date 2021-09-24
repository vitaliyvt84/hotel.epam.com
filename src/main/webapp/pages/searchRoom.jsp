<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<link href="<c:url value="/css/create_form.css"/>" rel="stylesheet" type="text/css"/>--%>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

<html>
<head>
    <title>Search Room</title>
</head>
<body>
<jsp:include page="/pages/common/menu.jsp"/>

<%--<input type="hidden" name="form" value="search_room">--%>
<div class="container">
    <form class="search_room" name="search_room" method="post" action="${app}/searchRoom">
        <br/>
        <h4>Пожалуйста заполните следующие данные для поиска подходящих апартаментов: </h4>

        <div class="row mb-3">
            <label class="col-sm-2 col-form-label" for="check_in">Дата заезда:</label>
            <div class="col-sm-3">
                <input type="date" class="form-control" name="check_in" id="check_in" required/>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 col-form-label" for="check_out">Дата выезда:</label>
            <div class="col-sm-3">
                <input type="date" class="form-control" name="check_out" id="check_out" required/>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Количество комнат:</label>
            <div class="col-sm-3">
                <select class="form-select" size="4" multiple name="count_of_room">
                    <option disabled>Выберите кол-во комнат</option>
                    <option selected value="1">1</option>
                    <c:if test="${maxRoomNumber > 1}">
                        <c:forEach begin="2" end="${maxRoomNumber}" varStatus="position">
                            <option value="${position.current}">${position.current}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Количество взрослых:</label>
            <div class="col-sm-3">
                <select class="form-select" size="4" multiple name="number_of_adult">
                    <option disabled>Выберите кол-во взрослых</option>
                    <option selected value="1">1</option>
                    <c:if test="${maxAdultNumber > 1}">
                        <c:forEach begin="2" end="${maxAdultNumber}" varStatus="position">
                            <option value="${position.current}">${position.current}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Количество детей:</label>
            <div class="col-sm-3">
                <select class="form-select" size="4" multiple name="number_of_child">
                    <option disabled>Выберите кол-во детей</option>
                    <option selected value="0">0</option>
                    <c:if test="${maxChildNumber > 0}">
                        <c:forEach begin="1" end="${maxChildNumber}" varStatus="position">
                            <option value="${position.current}">${position.current}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <button class="btn btn-primary btn-md" type="submit" name="submit" value="send">Отправить</button>
    </form>
</div>

<jsp:include page="/pages/common/footer.jsp"/>
</body>
</html>

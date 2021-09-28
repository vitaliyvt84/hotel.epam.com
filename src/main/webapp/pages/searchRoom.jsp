<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pages/common/menu.jspf" %>

<html>
<head>
    <title><fmt:message key="searchRoom" /></title>
</head>
<body>
<div class="container">
    <form class="search_room" name="search_room" method="post" action="${app}/searchRoom">
        <br/>
        <h4><fmt:message key="searchRoomMessage" />: </h4>

        <div class="row mb-3">
            <label class="col-sm-2 col-form-label" for="check_in"><fmt:message key="arrivalDate" />:</label>
            <div class="col-sm-3">
                <input type="date" class="form-control" name="check_in" id="check_in" required/>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 col-form-label" for="check_out"><fmt:message key="dateOfDeparture" />:</label>
            <div class="col-sm-3">
                <input type="date" class="form-control" name="check_out" id="check_out" required/>
            </div>
        </div>

        <div class="row mb-3">
            <label class="col-sm-2 col-form-label"><fmt:message key="numberOfRooms" />:</label>
            <div class="col-sm-3">
                <select class="form-select" size="4" multiple name="count_of_room">
                    <option disabled><fmt:message key="chooseNumberRooms" /></option>
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
            <label class="col-sm-2 col-form-label"><fmt:message key="numberOfAdult" />:</label>
            <div class="col-sm-3">
                <select class="form-select" size="4" multiple name="number_of_adult">
                    <option disabled><fmt:message key="chooseNumberOfAdult" /></option>
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
            <label class="col-sm-2 col-form-label"><fmt:message key="numberOfChild" />:</label>
            <div class="col-sm-3">
                <select class="form-select" size="4" multiple name="number_of_child">
                    <option disabled><fmt:message key="chooseNumberOfChild" /></option>
                    <option selected value="0">0</option>
                    <c:if test="${maxChildNumber > 0}">
                        <c:forEach begin="1" end="${maxChildNumber}" varStatus="position">
                            <option value="${position.current}">${position.current}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <button class="btn btn-primary btn-md" type="submit" name="submit" value="send"><fmt:message key="send" /></button>
    </form>
</div>

<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>
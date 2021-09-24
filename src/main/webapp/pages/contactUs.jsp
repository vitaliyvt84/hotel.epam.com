<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<link href="<c:url value="/css/create_form.css"/>" rel="stylesheet" type="text/css"/>--%>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<html>
<head>
    <title>Contact Us</title>
</head>
<jsp:include page="/pages/common/menu.jsp"/>
<div class="container p-3">
    <div class="card">
        <div class="card-title">
            <div class="p-3">
                <h5>Пожалуйста, если у Вас возникли какие либо вопросы или пожелания, напешите нам, или заполните форму и мы свяжемся с Вами.</h5>
            </div>
        </div>
        <div class="card-body">
            <h4>${infoMessage}</h4>
            <c:if test="${infoMessage == null}">
                Контакты:
                e-mail: hotel@mail.com

            </c:if>
            ${infoMessage = null}
            <br/>
            <form class="contact_us" name="contactUs" id="contactUs" method="post" action="${app}/contactUs">
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label">Имя и фамилия:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="name" id="name" placeholder="Введите имя и фамилию" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="e-mail" class="col-sm-2 col-form-label">E-mail:</label>
                    <div class="col-sm-7">
                        <input type="email" class="form-control" name="e-mail" id="e-mail" placeholder="email@mail.com" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label">Контактный телефон:</label>
                    <div class="col-sm-7">
                        <input type="tel" class="form-control" name="phone" id="phone" placeholder="3807777777" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="subject" class="col-sm-2 col-form-label">Тема:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="subject" id="subject" placeholder="тема" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="message" class="col-sm-2 col-form-label">Сообщение:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="message" id="message" placeholder="собщение" required>
                    </div>
                </div>
                <button class="btn btn-primary" form="contactUs" type="submit" name="submit">Отправить</button>
                <button class="btn btn-primary" form="contactUs" type="reset" name="reset" >Очистить форму</button>
            </form>
        </div>
    </div>
</div>



<jsp:include page="/pages/common/footer.jsp"/>
</body>
</html>

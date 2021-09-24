<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/css/create_form.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

<html>
<head>
    <title>Регистрация</title>
    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("email").onchange = validateEmail;
            document.getElementById("password").onchange = validatePassword;
            document.getElementById("repassword").onchange = validatePassword;
        }
        function validatePassword() {
            var pass = document.getElementById("password").value;
            var rePass = document.getElementById("repassword").value;
            if (pass != rePass) {
                document.getElementById("repassword").setCustomValidity("Пароли не идентичны!");
            } else {
                document.getElementById("repassword").setCustomValidity('');
            }
        }
        function validateEmail() {
            var email = document.getElementById("email").value;
            if (email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
                document.getElementById("email").setCustomValidity('');
            } else {
                document.getElementById("email").setCustomValidity("Wrong email!");
            }
        }
    </script>
</head>
<body>
<jsp:include page="/pages/common/menu.jsp"/>


<form class="form p-4" name="registration" method="post" action="${app}/registration">
    <div class="container p-3 bg-light">
        <h3>Регистрация</h3>
        <div class="card">
            <div class="card-body">
                <div class="message">${sessionScope.message}</div>
                <div class="form-group row">
                    <label for="login" class="col-sm-2 col-form-label">Login:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/login.png"></span>
                        <input type="text" class="form-control" name="login" id="login" placeholder="Login" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">E-mail:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/email.png"></span>
                        <input type="text" class="form-control" name="email" id="email" placeholder="e-mail" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="userName" class="col-sm-2 col-form-label">Имя и Фамилия:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/name.png"></span>
                        <input type="text" class="form-control" name="userName" id="userName" placeholder="Имя и Фамилия" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label">Телефонный номер:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/call1.png"></span>
                        <input type="text" class="form-control" name="phone" id="phone" placeholder="387776665544" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="address" class="col-sm-2 col-form-label">Домашний адрес:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/home.png"></span>
                        <input type="text" class="form-control" name="address" id="address" placeholder="Домашний адрес" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Пароль:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/password.png"></span>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Пароль" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="repassword" class="col-sm-2 col-form-label">Повторите пароль:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/password.png"></span>
                        <input type="password" class="form-control" name="repassword" id="repassword" placeholder="Повтор пароля" required>
                    </div>
                </div>
                <button class="btn btn-success" type="submit">Сохранить</button>
            </div>
        </div>
    </div>

</form>
<jsp:include page="/pages/common/footer.jsp"/>
</body>
</html>

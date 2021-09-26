<%@ include file="/pages/common/menu.jspf" %>

<html>
<head>
    <title><fmt:message key="registration" /></title>
    <script type="text/javascript" src="${app}/js/register.js"></script>
</head>
<body>

<form class="form p-4" name="registration" method="post" action="${app}/registration">
    <div class="container p-3 bg-light">
        <h3><fmt:message key="registration" /></h3>
        <div class="card">
            <div class="card-body">
                <div class="message">${sessionScope.message}</div>
                <div class="form-group row">
                    <label for="login" class="col-sm-2 col-form-label"><fmt:message key="authLogin" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/login.png"></span>
                        <input type="text" class="form-control" name="login" id="login" placeholder="<fmt:message key="authLogin" />" required>
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
                    <label for="userName" class="col-sm-2 col-form-label"><fmt:message key="nameSurname" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/name.png"></span>
                        <input type="text" class="form-control" name="userName" id="userName" placeholder="<fmt:message key="nameSurname" />" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label"><fmt:message key="phone" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/call1.png"></span>
                        <input type="text" class="form-control" name="phone" id="phone" placeholder="387776665544" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="address" class="col-sm-2 col-form-label"><fmt:message key="homeAddress" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/home.png"></span>
                        <input type="text" class="form-control" name="address" id="address" placeholder="<fmt:message key="homeAddress" />" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label"><fmt:message key="password" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/password.png"></span>
                        <input type="password" class="form-control" name="password" id="password" placeholder="<fmt:message key="password" />" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="repassword" class="col-sm-2 col-form-label"><fmt:message key="returnPassword" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/password.png"></span>
                        <input type="password" class="form-control" name="repassword" id="repassword" placeholder="<fmt:message key="returnPassword" />" required>
                    </div>
                </div>
                <button class="btn btn-success" type="submit"><fmt:message key="save" /></button>
            </div>
        </div>
    </div>

</form>
<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>

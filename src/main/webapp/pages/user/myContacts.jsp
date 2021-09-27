<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pages/common/menu.jspf" %>

<html>
<head>
    <title><fmt:message key="personalData" /></title>

</head>
<body>

<form class="form p-4" name="personal_data">
    <div class="container p-3 bg-light">
        <h3><fmt:message key="personalData" /></h3>
        <div class="card">
            <div class="card-body">
                <div class="form-group row">
                    <label for="login" class="col-sm-2 col-form-label"><fmt:message key="login" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/login.png"></span>
                        <input type="text" class="form-control" name="login" id="login" placeholder="${user.login}" disabled>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">E-mail:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/email.png"></span>
                        <input type="text" class="form-control" name="email" id="email" placeholder="${user.email}" disabled>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="userName" class="col-sm-2 col-form-label"><fmt:message key="nameSurname" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/name.png"></span>
                        <input type="text" class="form-control" name="userName" id="userName" value="${user.name}" disabled>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label"><fmt:message key="phone" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/call1.png"></span>
                        <input type="text" class="form-control" name="phone" id="phone" value="${user.phone}" disabled>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="address" class="col-sm-2 col-form-label"><fmt:message key="homeAddress" />:</label>
                    <div class="input-group flex-nowrap col-sm-6">
                        <span class="input-group-text"><img src="${app}/images/small/home.png"></span>
                        <input type="text" class="form-control" name="address" id="address" value="${user.address}" disabled>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>

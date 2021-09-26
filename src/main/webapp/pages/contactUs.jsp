<%@ include file="/pages/common/menu.jspf" %>
<html>
<head>
    <title><fmt:message key="contactUs" /></title>
</head>

<div class="container p-3">
    <div class="card">
        <div class="card-title">
            <div class="p-3">
                <h5><fmt:message key="contactUsMessage" />.</h5>
            </div>
        </div>
        <div class="card-body">
            <h4>${infoMessage}</h4>
            <c:if test="${infoMessage == null}">
                <fmt:message key="contacts" />:
                e-mail: hotel@mail.com

            </c:if>
            ${infoMessage = null}
            <br/>
            <form class="contact_us" name="contactUs" id="contactUs" method="post" action="${app}/contactUs">
                <div class="form-group row">
                    <label for="name" class="col-sm-2 col-form-label"><fmt:message key="nameSurname" />:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="name" id="name" placeholder="<fmt:message key="inputNameSurname" />" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="e-mail" class="col-sm-2 col-form-label">E-mail:</label>
                    <div class="col-sm-7">
                        <input type="email" class="form-control" name="e-mail" id="e-mail" placeholder="email@mail.com" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label"><fmt:message key="contactPhone" />:</label>
                    <div class="col-sm-7">
                        <input type="tel" class="form-control" name="phone" id="phone" placeholder="3807777777" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="subject" class="col-sm-2 col-form-label"><fmt:message key="subject" />:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="subject" id="subject" placeholder="<fmt:message key="subject" />" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="message" class="col-sm-2 col-form-label"><fmt:message key="message" />:</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="message" id="message" placeholder="<fmt:message key="message" />" required>
                    </div>
                </div>
                <button class="btn btn-primary" form="contactUs" type="submit" name="submit"><fmt:message key="send" /></button>
                <button class="btn btn-primary" form="contactUs" type="reset" name="reset" ><fmt:message key="clearTheForm" /></button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>

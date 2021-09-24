<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value="/css/create_form.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

<html>
<head>
    <title>Payment</title>
</head>
<body>
<jsp:include page="/pages/common/menu.jsp"/>

<div class="container">
    <h4>Пожалуйста введите платежные данные и нажмите 'Оплатить' или нажмите 'Отменить'!</h4>
    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <img src="${app}/images/common/credit_card.jpg" class="img-thumbnail" width="203" height="62">
            </div>
            <form class="payment" name="payOrder" method="post" id="payOrder" action="${app}/payment">

                <div class="row g-3">
                    <label class="form-label" for="cardType">Тип кредитной карты:</label>
                    <select class="form-select" size="1" name="cardType" id="cardType">
                        <option selected value="visa">Visa</option>
                        <option selected value="master_card">MasterCard</option>
                    </select>
                </div>
                <div class="row g-3">
                    <label for="cardNumber">Номер кредитной карты:</label>
                    <input type="text" name="cardNumber" id="cardNumber" pattern="[0-9]{16}" maxlength="16" placeholder="xxxx xxxx xxxx xxxx" required/>
                </div>
                <div class="row g-3">
                    <label for="expireDate">Срок действия карты:</label>
                    <input type="text" name="month-year" id="expireDate" pattern="(?:0[1-9]|1[0-2])/[0-9]{2}" placeholder="MM/YY" required/>
                </div>
                <div class="row g-3">
                    <label for="cvc">Cvc:</label>
                    <input type="password" name="cvc" id="cvc" pattern="[0-9]{3}" maxlength="3" placeholder="***" required/>
                </div>
                <div class="row g-3">
                    <label for="nameOnCard">Имя владельца карты:</label>
                    <input type="text" name="nameOnCard" id="nameOnCard" required/>
                </div>
                <div class="row g-3">
                    <label><strong>Сумма к оплате:</strong></label>
                    <h4><strong> ${bookingDTO.price}</strong></h4>
                </div>
                <div class="row g-3">
                    <button class="btn btn-primary btn-md" form="payOrder" type="submit" name="pay" value="payOk">Оплатить</button>
                </div>
            </form>
            <form class="payment" name="payCancel" method="post" id="payCancel" action="${app}/payment">
                <button class="btn btn-primary btn-md" type="submit" name="pay" value="payCancel">Отменить заказ</button>
            </form>
        </div>
    </div>
    <jsp:include page="/pages/common/footer.jsp"/>
</div>

</body>
</html>

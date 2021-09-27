<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pages/common/menu.jspf" %>

<html>
<head>
    <title><fmt:message key="payment" /></title>
</head>
<body>


<div class="container">
    <h4><fmt:message key="paymentMessage" />!</h4>
    <div class="card">
        <div class="card-body">
            <div class="form-group row">
                <img src="${app}/images/common/credit_card.jpg" class="img-thumbnail" width="203" height="62">
            </div>
            <form class="payment" name="payOrder" method="post" id="payOrder" action="${app}/payment">

                <div class="row g-3">
                    <label class="form-label" for="cardType"><fmt:message key="creditCardType" />:</label>
                    <select class="form-select" size="1" name="cardType" id="cardType">
                        <option selected value="visa">Visa</option>
                        <option selected value="master_card">MasterCard</option>
                    </select>
                </div>
                <div class="row g-3">
                    <label for="cardNumber"><fmt:message key="creditCardNumber" />:</label>
                    <input type="text" name="cardNumber" id="cardNumber" pattern="[0-9]{16}" maxlength="16" placeholder="xxxx xxxx xxxx xxxx" required/>
                </div>
                <div class="row g-3">
                    <label for="expireDate"><fmt:message key="cardExpiryDate" />:</label>
                    <input type="text" name="month-year" id="expireDate" pattern="(?:0[1-9]|1[0-2])/[0-9]{2}" placeholder="MM/YY" required/>
                </div>
                <div class="row g-3">
                    <label for="cvc">Cvc:</label>
                    <input type="password" name="cvc" id="cvc" pattern="[0-9]{3}" maxlength="3" placeholder="***" required/>
                </div>
                <div class="row g-3">
                    <label for="nameOnCard"><fmt:message key="cardholderName" />:</label>
                    <input type="text" name="nameOnCard" id="nameOnCard" required/>
                </div>
                <div class="row g-3">
                    <label><strong><fmt:message key="amountToPay" />:</strong></label>
                    <h4><strong> ${bookingDTO.price}</strong></h4>
                </div>
                <div class="row g-3">
                    <button class="btn btn-primary btn-md" form="payOrder" type="submit" name="pay" value="payOk"><fmt:message key="pay" /></button>
                </div>
            </form>
            <form class="payment" name="payCancel" method="post" id="payCancel" action="${app}/payment">
                <button class="btn btn-primary btn-md" type="submit" name="pay" value="payCancel"><fmt:message key="cancelOrder" /></button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>
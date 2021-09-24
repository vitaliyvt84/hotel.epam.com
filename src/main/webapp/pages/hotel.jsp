<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>

<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="common/menu.jsp"/>
<br/>
<h5>${payMessage}</h5>
<br/>
<div class="row justify-content-md-center">
    <div class="card p-3">
        <div class="card-body">
            <img src="${app}/images/common/1846.jpg" align="center">
        </div>
    </div>
</div>



<jsp:include page="/pages/common/footer.jsp"/>
</body>
</html>

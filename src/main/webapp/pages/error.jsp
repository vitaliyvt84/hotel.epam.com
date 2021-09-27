<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/pages/common/menu.jspf" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

    <h2>Error!</h2>
    ${sessionScope.errorMessage}
<%@ include file="/pages/common/footer.jspf" %>
</body>
</html>

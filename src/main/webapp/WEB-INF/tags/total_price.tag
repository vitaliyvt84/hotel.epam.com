<%@attribute name="num_ad" type="java.lang.Integer" required="true"%>
<%@attribute name="num_ch" type="java.lang.Integer" required="true"%>
<%@attribute name="ap_price" type="java.lang.Double" required="true"%>
<%@attribute name="num_day" type="java.lang.Long" required="true"%>

<%--Count total price for room--%>
${((num_ad * ap_price) + (num_ch * (ap_price / 2))) * num_day}
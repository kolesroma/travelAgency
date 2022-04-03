<%@ attribute name="originalPrice" required="true" type="java.lang.Integer" description="full price of order" %>
<%@ attribute name="disount" required="true" type="java.lang.Integer" description="discount for order [%]" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:formatNumber type="number" maxFractionDigits="0" groupingUsed = "false">
    ${originalPrice * (100 - disount) / 100}
</fmt:formatNumber>
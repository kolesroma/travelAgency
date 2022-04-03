<%@ attribute name="originalPrice" required="true" type="java.lang.Integer" description="full price of order" %>
<%@ attribute name="disount" required="true" type="java.lang.Integer" description="discount for order" %>

${originalPrice * (100 - disount) / 100}<br>
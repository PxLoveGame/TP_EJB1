<%--
  Created by IntelliJ IDEA.
  User: achaillot
  Date: 07/11/18
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  Hello World !
  <jsp:useBean id="converter" class="ConverterBean"></jsp:useBean>
  <%
    double result = converter.euroToOtherCurrency(100,"USD");
    out.println(result);
  %>
  </body>
</html>

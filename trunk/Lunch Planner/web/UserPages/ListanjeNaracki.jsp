<%-- 
    Document   : Listanje na narackite
    Created on : Jan 8, 2012, 2:29:50 AM
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Листање на нарачките</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <p><jsp:include page="ListanjeNarackiSoKomentari.jsp"/></p>
        <p><a href="MainPage.jsp">Кон главна срана</a></p>
    </body>
</html>

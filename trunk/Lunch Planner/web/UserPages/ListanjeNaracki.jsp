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
        <title>Listanje na narackite</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <jsp:include page="ListanjeNarackiSoKomentari.jsp"/>
        <a href="MainPage.jsp">Nazad</a>
    </body>
</html>

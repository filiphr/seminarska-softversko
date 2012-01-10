<%-- 
    Document   : Azuriranje
    Created on : Jan 3, 2012, 2:19:15 PM
    Author     : pc
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Ажурирање на податоци</title>
        <style type="text/css">
            a {
                font-size: medium;
                text-decoration: none;
                color: black;
            }
        </style>
    </head>


    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <br/>
            <br/>
            <a href="AddRemoveUser.jsp">Ажурирање на листа на корисници</a>
            <br/>
            <br/>
            <a href="UpdateUser.jsp">Промена на податоци на корисник</a>
            <br/>
            <br/>
            <a href="AddRemoveRestorant.jsp">Ажурирање на листа на ресторанти</a>
            <br/>
            <br/>
            <a href="AddRemoveItem.jsp">Ажурирање на мени на ресторантите</a>
            <br/>
            <br/>
            <a href="Arhiviraj.do">Архивирање на групите</a>
            <%-- TUKA TREBA DA SE BUTNE ARHIVIRANJE NA GRUPITE --%>
        </div>
    </body>
</html>

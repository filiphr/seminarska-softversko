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
        <div>
            <jsp:include page="../header.jsp"/>
            <p><a href="AddRemoveUser.jsp">Ажурирање на листа на корисници</a></p>
            <p><a href="UpdateUser.jsp">Промена на податоци на корисник</a></p>
            <p><a href="AddRemoveRestorant.jsp">Ажурирање на листа на ресторанти</a></p>
            <p><a href="AddRemoveItem.jsp">Ажурирање на мени на ресторантите</a></p>
            <p><a href="NEKADE_VO_NOKJTA.jsp">Архивирање на групите</a></p>
            <%-- TUKA TREBA DA SE BUTNE ARHIVIRANJE NA GRUPITE --%>
        </div>
    </body>
</html>

<%-- 
    Document   : header
    Created on : 05-Jan-2012, 19:04:57
    Author     : Filip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/HeaderCSS.css"/>
    </head>
    <body>
        <table> 
            <tr>
                <td rowspan="3"><img src="/Lunch_Planner/nca-logo-home.GIF"/></td>
                <td>
                    <a href="/Lunch_Planner/Logout.do">Одјава</a>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/Lunch_Planner/Preferences.jsp">Преференци</a>
                </td>
            </tr>
            <tr>
                <td><a href="/Lunch_Planner/UserPages/Create.jsp">Креирај настан</a>
            </tr>
        </table>
    </body>
</html>

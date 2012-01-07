<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DataBase.DataBaseHelper" %>
<%
    String userID = (String) request.getSession().getAttribute("username");
    boolean hasCreated = DataBaseHelper.hasCreatedEvent(userID);
    
%>
<link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/HeaderCSS.css"/>
<table id="header"> 
    <tr>
        <td rowspan="3"><img src="/Lunch_Planner/nca-logo-home.GIF"/></td>
        <td>
            <a id="whiteLink" href="/Lunch_Planner/Logout.do">Одјава</a>
        </td>
    </tr>
    <tr>
        <td>
            <a id="whiteLink" href="/Lunch_Planner/UserPages/Preferences.jsp">Преференци</a>
        </td>
    </tr>
    <tr>
        <td><a id="whiteLink" href="/Lunch_Planner/UserPages/Create.jsp">Креирај настан</a>
    </tr>
</table>

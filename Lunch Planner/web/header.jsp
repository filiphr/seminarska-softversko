<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DataBase.DataBaseHelper" %>
<%
    String userID = (String) request.getSession().getAttribute("username");
    boolean hasCreated = DataBaseHelper.hasCreatedEvent(userID);
    String createdLink = "";
    String createdText = "";
    if (hasCreated) {
        createdLink = "/Lunch_Planner/UserPages/CreateModify.jsp?what=modify";
        createdText = "Измени настан";
    } else {
        createdLink = "/Lunch_Planner/UserPages/CreateModify.jsp?what=create";
        createdText = "Креирај настан";
    }
    String preferenceLink = "";
    String preferenceText = "";
    boolean preferenceStrana = false;
    String url = request.getRequestURI();
    if (url.contains("Preferences.jsp")) {
        preferenceStrana = true;
    }
    if (!preferenceStrana) {
        preferenceLink = "/Lunch_Planner/UserPages/Preferences.jsp";
        preferenceText = "Преференци";
    }
    String administratorLink = "";
    String administratorText = "";

    boolean administrator = true;
    if (administrator) {
        administratorLink="/Lunch_Planner/AdminPages/Azuriranje.jsp";
        administratorText="Администрација на апликацијата";
    }

%>
<link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/HeaderCSS.css"/>
<table id="header"> 
    <tr>
        <td rowspan="3"><img src="/Lunch_Planner/nca-logo-home.GIF"/></td>
        <td>
            <a id="whiteLink" href="/Lunch_Planner/UserPages/MainPage.jsp">Главна страна</a>
        </td>
        <td>
            <a id="whiteLink" href="/Lunch_Planner/Logout.do">Одјава</a>
        </td>
    </tr>
    <tr>
        <td>
            <a id="whiteLink" href="<%= administratorLink %>"><%= administratorText %></a>
        </td>
        <td>
            <a id="whiteLink" href="<%=  preferenceLink%>"><%= preferenceText%></a>
        </td>
    </tr>
    <tr>
        <td>
            <a id="whiteLink" href="<%= createdLink%>"><%= createdText%></a>
        </td>
        <td>
        </td>
    </tr>
</table>

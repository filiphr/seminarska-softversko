<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DataBase.DataBaseHelper" %>
<%
    String userID = "";
    synchronized (session) {
        userID = (String) request.getSession().getAttribute("username");
    }
    boolean hasCreated = DataBaseHelper.hasCreatedEvent(userID);
    boolean hasOrdered = DataBaseHelper.IsNaracka(userID);
    String createdLink = "";
    String createdText = "";
    if (!hasCreated && !hasOrdered) {
        createdLink = "/Lunch_Planner/UserPages/Create.jsp";
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

    boolean administrator = DataBaseHelper.isAdministrator(userID);
    if (administrator) {
        administratorLink = "/Lunch_Planner/AdminPages/Azuriranje.jsp";
        administratorText = "Администрација на апликацијата";
    }

%>
<link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/GlavnaUreduvackaCSS.css"/>
<link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/HeaderCSS.css"/>
<link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/predict.css"/>
<table class="header"> 
    <tr>
        <td rowspan="3"><img src="/Lunch_Planner/nca-logo-home.GIF"/></td>
        <td>
            <a class="whiteLink" href="/Lunch_Planner/UserPages/MainPage.jsp">Главна страна</a>
        </td>
        <td>
            <a class="whiteLink" href="/Lunch_Planner/Logout.do">Одјава</a>
        </td>
    </tr>
    <tr>
        <td>
            <a class="whiteLink" href="<%= administratorLink%>"><%= administratorText%></a>
        </td>
        <td>
            <a class="whiteLink" href="<%=  preferenceLink%>"><%= preferenceText%></a>
        </td>
    </tr>
    <tr>
        <td>
            <a class="whiteLink" href="<%= createdLink%>"><%= createdText%></a>
        </td>
        <td>
            <a class="whiteLink" href="/Lunch_Planner/UserPages/Statistika.jsp">Корисничка историја</a>
        </td>
    </tr>
</table>

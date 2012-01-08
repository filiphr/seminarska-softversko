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
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <table>
        <%
        String user = (String)request.getSession().getAttribute("username");
        int group = Integer.parseInt(request.getParameter("IDGroup"));
        List<List<String>> lst = DataBaseHelper.getNameSNameLunchAndKoments(group);
        List<String> resVreme = DataBaseHelper.getRestoranAndVreme(group);
        String Ime = DataBaseHelper.getUserIme(user);
        String Prezime = DataBaseHelper.getUserPrezime(user);
        String Broj = DataBaseHelper.getRestoranTelefon(resVreme.get(0));
        String Adresa = DataBaseHelper.getRestoranAddress(resVreme.get(0));
        %>
        <tr>
            <td><b>Kreator: </b></td>
            <td> <%=Ime%> <%=Prezime%> </td>
        </tr>
        <tr>
            <td><b>Restoran: </b></td>
            <td><%=resVreme.get(0)%> </td>
        </tr>
        <tr>
            <td><b>Vreme: </b></td>
            <td><%=resVreme.get(1)%> </td>
        </tr>
        <tr>
            <td><b>Adresa: </b></td>
            <td><%=Adresa%> </td>
        </tr>
        <tr>
            <td><b>Telefon: </b></td>
            <td><%=Broj%> </td>
        </tr>
        <tr>
            <td><b>Ime i Prezime </b></td>
            <td><b>Jadenje </b></td>
            <td><b>Komentar </b></td>
        </tr>
        <%
        for(int i = 0; i<lst.get(0).size(); i++)
                       {
        %>
        <tr>
            <td><%=lst.get(0).get(i)%>   <%=lst.get(1).get(i)%></td>
            <td><%=lst.get(2).get(i)%></td>
            <td><%=lst.get(3).get(i)%></td>
        </tr>
        <% } %>
        </table>
        <a href="MainPage.jsp">Nazad</a>
    </body>
</html>

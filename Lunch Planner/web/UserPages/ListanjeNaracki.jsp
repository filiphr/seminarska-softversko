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
<table>
    <%
        String IDGroup2 = request.getParameter("groupID");
        //get the Group ID for the order
       // Integer IDGroup = Integer.parseInt(request.getParameter("groupID"));
        if (IDGroup2 != null) {
            int g = Integer.parseInt(IDGroup2);
            String u = DataBaseHelper.getGroupCreator(g);
            String ImeCreator = DataBaseHelper.getUserIme(u);
            String PrezimeCreator = DataBaseHelper.getUserPrezime(u);
            List<List<String>> Names = DataBaseHelper.getNameSNameLunchAndKoments(g);
            List<String> lst1 = DataBaseHelper.getRestoranAndVreme(g);
            String Restoran = lst1.get(0);
            String Vreme = lst1.get(1);
            String Adresa = DataBaseHelper.getRestoranAddress(Restoran);
            String Telefon = DataBaseHelper.getRestoranTelefon(Restoran);
            for (int i = 0; i < Names.get(0).size(); i++) {
                String Name = Names.get(0).get(i);
                String SName = Names.get(1).get(i);
                String Lunch = Names.get(2).get(i);
                String Komentar = Names.get(3).get(i);
                if (i == 0) {
    %>
    <tr>
            <td><b>Kreator: </b></td>
            <td> <%=ImeCreator%> <%=PrezimeCreator%> </td>
    </tr>
    <tr>
        <td><b> Restoran: </b></td> 
        <td> <%=Restoran%> </td>
    </tr>
    <tr>
        <td><b> Време </b></td>
        <td> <%=Vreme%> </td>
    </tr>
    <tr>
        <td><b> Адреса: </b></td>
        <td> <%=Adresa%> </td>
    </tr>
    <tr>
        <td><b> Телефон: </b></td>
        <td> <%=Telefon%> </td>
    </tr>
    <tr>
        <td><b>Име и презиме    </b></td>
        <td><b>     Јадење</b></td>
        <td><b>Коментар</b></td>
    </tr>
    <% }%>
    <tr>
        <td> <%=Name%>  <%= SName%>     </td>
        <td>      <%=Lunch%></td>
        <td> <%=Komentar%> </td>
    </tr>
    <% }}%>
</table>
        <a href="MainPage.jsp">Nazad</a>
    </body>
</html>

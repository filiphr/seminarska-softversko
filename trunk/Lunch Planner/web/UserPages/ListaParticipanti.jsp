<%-- 
    Document   : ListaParticipanti
    Created on : 08-Jan-2012, 16:10:13
    Author     : Filip
--%>

<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table>
    <%
        String IDGroup2 = request.getParameter("groupID");
        //get the Group ID for the order
       // Integer IDGroup = Integer.parseInt(request.getParameter("groupID"));
        if (IDGroup2 != null) {
            int g = Integer.parseInt(IDGroup2);
            List<List<String>> Names = DataBaseHelper.getNameSNameAndLunch(g);
            List<String> lst1 = DataBaseHelper.getRestoranAndVreme(g);
            String Restoran = lst1.get(0);
            String Vreme = lst1.get(1);
            String Adresa = DataBaseHelper.getRestoranAddress(Restoran);
            String Telefon = DataBaseHelper.getRestoranTelefon(Restoran);
            for (int i = 0; i < Names.get(0).size(); i++) {
                String Name = Names.get(0).get(i);
                String SName = Names.get(1).get(i);
                String Lunch = Names.get(2).get(i);
                if (i == 0) {
    %>
    <tr>
        <td><b> Ресторан: </b></td> 
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
    </tr>
    <% }%>
    <tr>
        <td> <%=Name%>  <%= SName%>     </td>
        <td>      <%=Lunch%></td>
    </tr>
    <% }
                            }%>
</table>
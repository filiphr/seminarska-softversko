<%-- 
    Document   : ListaParticipanti
    Created on : 08-Jan-2012, 16:10:13
    Author     : Filip
--%>

<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
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
        <td><b> Restoran: </b></td> 
        <td> <%=Restoran%> </td>
    </tr>
    <tr>
        <td><b> Vreme: </b></td>
        <td> <%=Vreme%> </td>
    </tr>
    <tr>
        <td><b> Adresa: </b></td>
        <td> <%=Adresa%> </td>
    </tr>
    <tr>
        <td><b> Telefon: </b></td>
        <td> <%=Telefon%> </td>
    </tr>
    <tr>
        <td><b>Ime i Prezime     </b></td>
        <td><b>     Jadenje</b></td>
    </tr>
    <% }%>
    <tr>
        <td> <%=Name%>  <%= SName%>     </td>
        <td>      <%=Lunch%></td>
    </tr>
    <% }
                            }%>
</table>
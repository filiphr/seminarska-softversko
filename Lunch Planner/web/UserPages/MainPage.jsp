<%-- 
    Document   : MainPage
    Created on : Jan 6, 2012, 5:13:07 PM
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="DataBase.DataBaseHelper" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../MainPage/pro_dropdown_3.css" />

        <script src="../MainPage/stuHover.js" type="text/javascript"></script>
    </head>
    <body>
        <a href="Preferences.jsp">PREFERENCI</a>
        <table>
            <%
                String User = (String) request.getSession().getAttribute("username");
                List<String> lst = DataBaseHelper.getAllPokani(User);
                if (lst != null) {
                    for (int i = 0; i < lst.size(); i++) {
                        int ID_Grupa = Integer.parseInt(lst.get(i));
                        List<String> lst1 = DataBaseHelper.getRestoranAndVreme(ID_Grupa);
                        String Restoran = lst1.get(0);
                        String Vreme = lst1.get(1);
            %>
            <tr><td><form action="Naracka.do" method="post">
                        Vie ste pokaneti vo restoran <%= Restoran%> vo <%= Vreme%> casot   
                        <% if(!(DataBaseHelper.IsNaracka(User)))  {%>
                        <input type="submit" name="Potvrdi" value="Potvrdi"/>
                        <% } %>
                        <input type="submit" name="Otkazi" value="Otkazi"/>
                        <input type="hidden" name="ID_Grupa" value="<%= ID_Grupa%>"/>
                    </form>
                </td>
            </tr>
            <% }
                  
                }%>
        </table>
        <table>
            <tr>
                <td>
        <table>
            <%
                List<String> groups = DataBaseHelper.getAllGroups();
                for (int i = 0; i < groups.size(); i++) {
                    int ID_Group = Integer.parseInt(groups.get(i));
                    List<List<String>> Names = DataBaseHelper.getNameSNameAndLunch(ID_Group);
                    List<String> lst1 = DataBaseHelper.getRestoranAndVreme(ID_Group);
                    String Restoran = lst1.get(0);
                    String Vreme = lst1.get(1);

            %>
            <tr><td>
                    <ul id="nav">
                        <li class="top"><a class="top_link"><span>Restoran: <%= Restoran%> Vreme: <%= Vreme%></span></a></li>        
                        <li class="top"><a id="participanti" class="top_link"><span class="down">Participanti</span></a>
                            <ul class="sub">
                                <% for (int j = 0; j < Names.get(0).size() && j<5; j++) {
                                        String Name = Names.get(0).get(j);
                                        String SName = Names.get(1).get(j);
                                        String Lucnh = Names.get(2).get(j);
%>
                                <li> <a> <%= Name%>  <%= SName%>  -  <%= Lucnh %> </a></li>
                                <% }%>
                            </ul>
                    </ul>
                </td>
            </tr>
            <% for (int s = 0; s < Names.get(0).size() * 5 && s<25; s++) {
            %>
            <tr> <td> </td></tr>
            <%   }%>
            <% }%>
        </table>
                </td>
            </tr>
        </table>
    </body>
</html>

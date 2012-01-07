<%-- 
    Document   : MainPage
    Created on : Jan 6, 2012, 5:13:07 PM
    Author     : user
--%>

<%@page import="javax.xml.stream.events.Namespace"%>
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
        <jsp:include page="../header.jsp"/>
        <table>
            <%
                String User = (String) request.getSession().getAttribute("username");
                boolean Naracka = DataBaseHelper.IsNaracka(User);
                int Grupa = 0;
                if (Naracka) {
                    Grupa = DataBaseHelper.getGroupOdNaracka(User);
                }
                List<String> lst = DataBaseHelper.getAllPokani(User);
                if (lst != null) {
                    for (int i = 0; i < lst.size(); i++) {
                        int ID_Grupa = Integer.parseInt(lst.get(i));
                        List<String> lst1 = DataBaseHelper.getRestoranAndVreme(ID_Grupa);
                        String Restoran = lst1.get(0);
                        String Vreme = lst1.get(1);
            %>
            <tr><td><form action="Pokana.do" method="post">
                        Vie ste pokaneti vo restoran <%= Restoran%> vo <%= Vreme%> casot   
                        <% if (!Naracka) {%>
                        <input type="submit" name="Potvrda" value="Potvrdi"/>
                        <% }%>
                        <input type="submit" name="Potvrda" value="Otkazi"/>
                        <input type="hidden" name="ID_Grupa" value="<%=ID_Grupa%>"/>
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
                            if (Grupa != 0) {
                                List<List<String>> Namestmp = DataBaseHelper.getNameSNameAndLunch(Grupa);
                                List<String> lst1tmp = DataBaseHelper.getRestoranAndVreme(Grupa);
                                String Restorantmp = lst1tmp.get(0);
                                String Vremetmp = lst1tmp.get(1);

                        %>
                        <tr><td>
                                <ul id="nav">
                                    <li class="top"><a class="top_link"><span>Restoran: <%= Restorantmp%> Vreme: <%= Vremetmp%></span></a></li>        
                                    <li class="top"><a id="participanti" class="top_link"><span class="down">Participanti</span></a>
                                        <ul class="sub">
                                            <% for (int j = 0; j < Namestmp.get(0).size() && j < 5; j++) {
                                                    String Nametmp = Namestmp.get(0).get(j);
                                                    String SNametmp = Namestmp.get(1).get(j);
                                                    String Lucnhtmp = Namestmp.get(2).get(j);
                                            %>
                                            <li> <a> <%= Nametmp%>  <%= SNametmp%>  -  <%= Lucnhtmp%> </a></li>
                                            <% }%>
                                        </ul>
                                </ul>
                            </td>
                            <td>
                                <form name="Listanje" action="" method="post">
                                    <input type="hidden" name="IDGrupa" value="<%=Grupa%>"/>
                                    <input type="submit" name="Izlistaj" value="Izlistaj"/>
                                </form>
                            </td>
                            <td><form action="Izmeni.do" method="post">
                                    <input type="hidden" name="ID_Grupa" value="<%=Grupa%>"/>
                                    <input type="submit" name="Izmeni" value="Izmeni"/>
                                </form></td>
                            <td>
                                <form action="Izlezi.do" method="post">
                                    <input type="hidden" name="ID_Grupa" value="<%=Grupa%>"/>
                                    <input type="submit" name="Izlezi" value="Izlezi"/>
                                </form>
                            </td>
                        </tr>
                        <% for (int s = 0; s < Namestmp.get(0).size() * 5 && s < 25; s++) {
                        %>
                        <tr> <td> </td></tr>
                        <%   }%>
                        <% }
                            List<String> groups = DataBaseHelper.getAllGroups();
                            for (int i = 0; i < groups.size(); i++) {
                                int ID_Group = Integer.parseInt(groups.get(i));
                                if (ID_Group != Grupa) {
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
                                            <% for (int j = 0; j < Names.get(0).size() && j < 5; j++) {
                                                    String Name = Names.get(0).get(j);
                                                    String SName = Names.get(1).get(j);
                                                    String Lucnh = Names.get(2).get(j);
                                            %>
                                            <li> <a> <%= Name%>  <%= SName%>  -  <%= Lucnh%> </a></li>
                                            <% }%>
                                        </ul>
                                </ul>
                            </td>
                            <td>
                                <form name="Listanje" action="" method="post">
                                    <input type="hidden" name="IDGrupa" value="<%=ID_Group%>"/>
                                    <input type="submit" name="Izlistaj" value="Izlistaj"/>
                                </form>
                            </td>
                            <%
                                if (Naracka) {
                                    if (Grupa == ID_Group) {
                            %>
                            <td><form action="Izmeni.do" method="post">
                                    <input type="hidden" name="ID_Grupa" value="<%=ID_Group%>"/>
                                    <input type="submit" name="Izmeni" value="Izmeni"/>
                                </form></td>
                            <td>
                                <form action="Izlezi.do" method="post">
                                    <input type="hidden" name="ID_Grupa" value="<%=ID_Group%>"/>
                                    <input type="submit" name="Izlezi" value="Izlezi"/>
                                </form>
                            </td>
                            <% }
                            } else {%>
                            <td>
                                <form action="Join.do" method="post">
                                    <input type="hidden" name="ID_Grupa" value="<%=ID_Group%>"/>
                                    <input type="submit" name="Join" value="Join"/>
                                </form>
                            </td>
                            <% }%>

                        </tr>
                        <% for (int s = 0; s < Names.get(0).size() * 5 && s < 25; s++) {
                        %>
                        <tr> <td> </td></tr>
                        <%   }%>
                        <% }
                            }%>
                    </table>
                </td>
                <td>
                    <table>
                        <%
                            String str = request.getParameter("IDGrupa");
                            if (str != null) {
                                int g = Integer.parseInt(str);
                                List<List<String>> Names = DataBaseHelper.getNameSNameAndLunch(g);
                                List<String> lst1 = DataBaseHelper.getRestoranAndVreme(g);
                                String Restoran = lst1.get(0);
                                String Vreme = lst1.get(1);
                                String Adresa = DataBaseHelper.getRestoranAddress(Restoran);
                                String Telefon = DataBaseHelper.getRestoranTelefon(Restoran);
                                for (int i = 0; i < Names.get(0).size(); i++) {
                                    String Name = Names.get(0).get(i);
                                    String SName = Names.get(1).get(i);
                                    String Lucnh = Names.get(2).get(i);
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
                            <td>      <%=Lucnh%></td>
                        </tr>
                        <% }
                            }%>
                    </table>
                </td>
            </tr>            
        </table>
    </body>
</html>

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
        <title>Главна страна</title>
        <link rel="stylesheet" type="text/css" href="../MainPage/pro_dropdown_3.css" />
        <script src="../MainPage/stuHover.js" type="text/javascript"></script>
        <script type="text/javascript">
            function Potvrda()
            {
                
                return confirm("Дали сакате да ја избришете групата?");
            };
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <table>
                <%
                    String User = "";
                    synchronized (session) {
                        User = (String) request.getSession().getAttribute("username");
                    }
                    boolean hasCreator = DataBaseHelper.hasCreatedEvent(User);
                    boolean Naracka = DataBaseHelper.IsNaracka(User);
                    int Grupa = 0;
                    if (Naracka) {
                        Grupa = DataBaseHelper.getGroupOdNaracka(User);
                    }
                    if (hasCreator) {
                        Grupa = DataBaseHelper.getGroupIDFromCreator(User);
                    }
                    List<String> lst = DataBaseHelper.getAllPokani(User);
                    if (lst != null) {
                        for (int i = 0; i < lst.size(); i++) {
                            int ID_Grupa = Integer.parseInt(lst.get(i));
                            List<String> lst1 = DataBaseHelper.getRestoranAndVreme(ID_Grupa);
                            String Restoran = lst1.get(0);
                            String Vreme = lst1.get(1);
                            if (i == 0) {
                %>
                <tr>
                    <td> <b> Покани за придружување во група </b> </td>
                </tr>
                <% }%>
                <tr><td><form action="Pokana.do" method="post">
                            Вие сте поканети во ресторант <%= Restoran%> во <%= Vreme%> часот   
                            <% if (!Naracka) {%>
                            <input type="submit" name="Potvrda" value="Потврди"/>
                            <% }%>
                            <input type="submit" name="Potvrda" value="Откажи"/>
                            <input type="hidden" name="listaj" value="0"/>
                            <input type="hidden" name="ID_Grupa" value="<%=ID_Grupa%>"/>
                        </form>
                    </td>
                </tr>
                <% }
                    }
                    List<String> Notification = DataBaseHelper.getNotification(User);
                    for (int i = 0; i < Notification.size(); i++) {
                        if (i == 0) {
                %>
                <tr>
                    <td><b>Известувања</b></td>
                </tr>    
                <% }%>
                <tr>
                    <td>
                        <%= Notification.get(i)%>
                    </td>
                </tr>
                <% if (i == Notification.size() - 1) {
                %>
                <tr>
                    <td>
                        <form action="IzbrisiNotification.do" method="get">
                            <input type="hidden" name="listaj" value="0"/>
                            <input type="submit" value="Избриши известувања"/>
                        </form>
                    </td>
                </tr>
                <%}
                    }
                    int UserGroup = DataBaseHelper.getGroupIDFromCreator(User);
                    if (UserGroup != -1) {
                        List<String> UserRestoran = DataBaseHelper.getRestoranAndVreme(UserGroup);
                %>
                <tr>
                    <td>
                        Вие имате организирано јадење во <b><%= UserRestoran.get(0)%></b> во <b><%= UserRestoran.get(1)%> </b>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="ListanjeNaracki.jsp" method="post">
                            <input type="hidden" name="groupID" value="<%=UserGroup%>"/>
                            <input type="hidden" name="listaj" value="0"/>
                            <input type="submit" value="Излистај нарачки во групата"/>
                        </form>
                    </td>
                    <td>
                        <form id="IzbrisiForm" action="IzbrisiGroup.do" method="post" onsubmit="return Potvrda()">
                            <input type="hidden" name="groupID" value="<%=UserGroup%>"/>
                            <input type="hidden" name="listaj" value="0"/>
                            <input type="submit" name="izbrisi" value="Избриши група"/>
                            <b> Внесете причина: </b><INPUT TYPE=TEXT NAME="t1"/>
                        </form>
                    </td>
                </tr>
                <% }%>
            </table>
            <table>
                <tr>
                    <td>
                        <b> Креирани групи </b>
                    </td>
                </tr>
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
                            <tr>
                                <td>
                                    <form name="Listanje" action="" method="post">
                                        <input type="hidden" name="groupID" value="<%=Grupa%>"/>
                                        <input type="hidden" name="listaj" value="1"/>
                                        <input type="submit" name="Izlistaj" value="Излистај"/>
                                    </form>
                                </td>
                                <%  if (Naracka) {%>
                                <td><form action="Naracka.jsp?groupID=<%=Grupa%>&join=true" method="post">
                                        <input type="hidden" name="ID_Grupa" value="<%=Grupa%>"/>
                                        <input type="hidden" name="listaj" value="0"/>
                                        <input type="hidden" name="Izmeni" value="1"/>
                                        <input type="submit" name="Izmeni" value="Измени нарачка"/>
                                    </form></td>
                                <td>
                                    <form action="NarackaZaDrug.do" method="post">
                                        <input type="hidden" name="ID_Grupa" value="<%=Grupa%>"/>
                                        <input type="hidden" name="listaj" value="0"/>
                                        <input type="submit" value="Нарачај/измени за друг"/>
                                    </form>
                                </td>
                                <% if (UserGroup == -1) {%>
                                <td>
                                    <form action="Izlezi.do" method="post">
                                        <input type="hidden" name="ID_Grupa" value="<%=Grupa%>"/>
                                        <input type="hidden" name="listaj" value="0"/>
                                        <input type="submit" name="Izlezi" value="Излези од групата"/>
                                    </form>
                                </td>
                                <% }
                                } else {%>
                                <td>
                                    <form action="Join.do" method="post">
                                        <input type="hidden" name="ID_Grupa" value="<%=Grupa%>"/>
                                        <input type="hidden" name="listaj" value="0"/>
                                        <input type="submit" name="Join" value="Приклучу се"/>
                                    </form>
                                </td>
                            </tr> <% }%>
                            <tr><td colspan="4">
                                    <ul id="nav">
                                        <li class="top"><a class="top_link"><span>Ресторант: <%= Restorantmp%> Време: <%= Vremetmp%></span></a></li>        
                                        <li class="top"><a id="participanti" class="top_link"><span class="down">Партиципанти</span></a>
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
                            </tr>


                            <%
                                for (int s = 0; s < (Namestmp.get(0).size() * 5) - 4 && s < 20; s++) {
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
                            <tr>
                                <td>
                                    <form name="Listanje" action="" method="post">
                                        <input type="hidden" name="groupID" value="<%=ID_Group%>"/>
                                        <input type="hidden" name="listaj" value="1"/>
                                        <input type="submit" name="Izlistaj" value="Излистај"/>
                                    </form>
                                </td>
                                <%
                                    if (!Naracka && !hasCreator) {%>
                                <td>
                                    <form action="Join.do" method="post">
                                        <input type="hidden" name="ID_Grupa" value="<%=ID_Group%>"/>
                                        <input type="hidden" name="listaj" value="0"/>
                                        <input type="submit" name="Join" value="Приклучи се"/>
                                    </form>
                                </td>
                                <% }%>

                            </tr>
                            <tr><td colspan="4">
                                    <ul id="nav">
                                        <li class="top"><a class="top_link"><span>Ресторант: <%= Restoran%> Време: <%= Vreme%></span></a></li>        
                                        <li class="top"><a id="participanti" class="top_link"><span class="down">Партиципанти</span></a>
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
                            </tr>

                            <% for (int s = 0; s < (Names.get(0).size() * 5) - 10 && s < 20; s++) {
                            %>
                            <tr> <td> </td></tr>
                            <%   }%>
                            <% }
                                }%>
                        </table>
                    </td>
                    <td>
                        <% String str = (String) request.getParameter("listaj");
                            if (str != null && str.equals("1")) {
                        %>
                        <jsp:include page="ListanjeNarackiSoKomentari.jsp"/>
                        <% }%>
                    </td>
                </tr>            
            </table>
        </div>
    </body>
</html>

<%-- 
    Document   : Naracka
    Created on : 05-Jan-2012, 19:17:15
    Author     : Filip
--%>

<%@page import="sun.security.krb5.internal.KDCOptions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Naracka</title>

    </head>
    <%
        Integer IDGroup = Integer.parseInt(request.getParameter("groupID"));
        if (IDGroup == null) {
            // GroupID = "0";
        }
        Integer izmeni = Integer.parseInt(request.getParameter("Izmeni"));
        String komentar = (String)request.getParameter("Komentar");
        if (komentar == null) {
            komentar = new String();
        }
    %>



    <body>
        <script>
            function formSubmit(source)
            {
                var myForm=document.getElementById("naracka");
                myForm.elements["source"].value=source;
                myForm.submit();
            }            
        </script>
        <jsp:include page="../header.jsp"/>

        <table>
            <tr class="header">
                <td>

                </td>
            </tr>
            <tr class="menu" >
                <td>


                </td>
            </tr>
            <tr class="body" >
                <td>
                    <table border="2">
                        <tr>
                            <td>
                                Koj restoran, koe vreme, koi ucestvuvaat
                            </td>
                            <td>
                                Formata za naracka
                            </td>
                        </tr>
                        <tr>
                            <td>
                                listata so ucesnici
                            </td>
                            <td>          
                                <form method="POST" name="naracka" id="naracka" action="">
                                    <input type="hidden" name="source"/>
                                    <table>
                                        <tr>
                                            <td>                            
                                                <select  name="Meni" size="3" onchange="formSubmit('Meni')">
                                                    <option value="" disabled="true">Клик за да изберете јадење</option>
                                                    <%
                                                        String restaurant = DataBaseHelper.getRestaurantName(IDGroup);
                                                        List<List<String>> lst1 = DataBaseHelper.getAllMenuItemsAndPrice(restaurant);
                                                        for (int i = 0; i < lst1.get(0).size(); i++) {
                                                            String stavkaIme = lst1.get(0).get(i);
                                                            String stavkaCena = lst1.get(1).get(i);
                                                    %>
                                                    <option value="<%=stavkaIme%>"><%=stavkaIme%> <%=stavkaCena%> ден</option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="odbrani" size="3" onchange="formSubmit('odbrani')">
                                                    <option value="" disabled="true">Клик за да избришете ставка</option>
                                                    <%
                                                        List<String> Odbrani = (List<String>) session.getAttribute("Odbrani");
                                                        if (Odbrani == null) {
                                                            Odbrani = new ArrayList<String>();
                                                        }
                                                        String odbrano = request.getParameter("Meni");
                                                        String remove = request.getParameter("odbrani");

                                                        String pom = request.getParameter("source");

                                                        if ("Meni".equals(pom)) {
                                                            if (odbrano != null) {
                                                                Odbrani.add(odbrano);

                                                            }
                                                        } else if ("odbrani".equals(pom)) {
                                                            if (remove != null) {
                                                                Odbrani.remove(remove);
                                                            }
                                                        }
                                                        String user = (String) session.getAttribute("username");
                                                        if (izmeni == 1) {
                                                            List<String> lst3 = DataBaseHelper.getLunch(user, IDGroup);
                                                            for (int i = 0; i < lst3.size(); i++) {
                                                                Odbrani.add(lst3.get(i));
                                                            }
                                                            komentar = DataBaseHelper.getKomentar(user, IDGroup);
                                                            session.setAttribute("Odbrani", Odbrani);
                                                            response.sendRedirect("Naracka.jsp?groupID=" + IDGroup + "&Izmeni=0&Komentar="+komentar);
                                                        }
                                                        String prefRestaurant = DataBaseHelper.getPreferencesRestoran(user);
                                                        if (prefRestaurant != null && prefRestaurant.equals(DataBaseHelper.getRestaurantName(IDGroup))) {
                                                            String prefMeal = DataBaseHelper.getPreferencesMeal(user);
                                                            if (prefMeal != null && !Odbrani.contains(prefMeal)) {
                                                                Odbrani.add(prefMeal);
                                                            }
                                                        }

                                                        session.setAttribute("Odbrani", Odbrani);
                                                        for (int i = 0; i < Odbrani.size(); i++) {
                                                            String s = Odbrani.get(i);
                                                    %>
                                                    <option value="<%=s%>"><%=s%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                                <form method="post" action="Naracka.do">
                                    <input type="hidden" name="groupID" value="<%=IDGroup%>"/>
                                    <table>
                                        <tr>
                                            <td>
                                                Коментар: <input type="text" name="komentar" value="<%=komentar%>"/>
                                            </td>
                                            <td>
                                                <input type="submit" value="Naracaj"/>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr class="footer" >
                <td>

                </td>
            </tr>
        </table>

    </body>

</html>

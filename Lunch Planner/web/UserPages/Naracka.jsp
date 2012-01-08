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
        //get the Group ID for the order
        Integer IDGroup = Integer.parseInt(request.getParameter("groupID"));

        //Get the currently user in the session
        String username = (String) session.getAttribute("username");

        //User za kogo se pravi naracka, default e aktivniot user
        String OrderUser = request.getParameter("Naracuvac");
        if (OrderUser == null) {
            OrderUser = username;
        }

        //if join=true the username joins the group, he cannot make order for different person
        String join = request.getParameter("join");

        //izmeni=1 -user edits his order, izmeni=0 users creates order
        Integer izmeni = Integer.parseInt(request.getParameter("Izmeni"));
        String komentar = (String) request.getParameter("Komentar");
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
                                <jsp:include page="ListaParticipanti.jsp"/>
                            </td>
                            <td>          
                                <form method="POST" name="naracka" id="naracka" action="">
                                    <input type="hidden" name="source"/>
                                    <table>
                                        <tr>
                                            <td>                            
                                                <select  name="Naracuvac" size="3" onchange="formSubmit('Naracuvac')">
                                                    <option value="" disabled="disabled">Изберете во кое име сакате да нарачате</option>
                                                    <%
                                                        //Users without order
                                                        List<String> UsersNoOrder=new ArrayList<String>();


                                                        if ("false".equals(join)) {
                                                            //Find the users that are not in a group
                                                            UsersNoOrder = DataBaseHelper.getParticipantBezJadenje();

                                                            UsersNoOrder.remove(username);
                                                        } else if ("true".equals(join)) {
                                                            UsersNoOrder.add(username);
                                                        }

                                                        //Show the users that are not in a group
                                                        for (int i = 0; i < UsersNoOrder.size(); i++) {
                                                            String tmpUsername = UsersNoOrder.get(i);
                                                            String tmpName = DataBaseHelper.getUserIme(tmpUsername);
                                                            String tmpLastName = DataBaseHelper.getUserPrezime(tmpUsername);

                                                    %>
                                                    <option value="<%=tmpUsername%>" <% if (tmpUsername.equals(OrderUser)) {%> selected="selected" <%}%>><%=tmpName%> <%=tmpLastName%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                            <td>                            
                                                <select  name="Meni" size="3" onchange="formSubmit('Meni')">
                                                    <option value="" disabled="disabled">Клик за да изберете јадење</option>
                                                    <%
                                                        //Get the restaurant for the group with id=IDGroup(this group)
                                                        String restaurant = DataBaseHelper.getRestaurantName(IDGroup);
                                                        //Get the menu items and prices for the restaurant
                                                        List<List<String>> ItemsPrice = DataBaseHelper.getAllMenuItemsAndPrice(restaurant);
                                                        for (int i = 0; i < ItemsPrice.get(0).size(); i++) {
                                                            String stavkaIme = ItemsPrice.get(0).get(i);
                                                            String stavkaCena = ItemsPrice.get(1).get(i);
                                                    %>
                                                    <option value="<%=stavkaIme%>"><%=stavkaIme%> <%=stavkaCena%> ден</option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="odbrani" size="3" onchange="formSubmit('odbrani')">
                                                    <option value="" disabled="disabled">Клик за да избришете ставка</option>
                                                    <%
                                                        //Get the pending order items from the session
                                                        List<String> Odbrani = (List<String>) session.getAttribute("Odbrani");
                                                        if (Odbrani == null) {
                                                            Odbrani = new ArrayList<String>();
                                                        }

                                                        //You can only use your own preferences
                                                        if (username.equals(OrderUser)) {
                                                            //Get all the items in the restaurant for this group
                                                            List<String> ItemsInRestaurant = ItemsPrice.get(0);
                                                            //Get the prefered meal
                                                            String prefMeal = DataBaseHelper.getPreferencesMeal(username);
                                                            //Check whethee there is a prefered meal and it exists in the current restaurant
                                                            if (prefMeal != null && ItemsInRestaurant.contains(prefMeal) && !Odbrani.contains(prefMeal)) {
                                                                Odbrani.add(prefMeal);
                                                            }
                                                        }

                                                        //For AutoPostBack get the chosen item from the menu
                                                        String odbrano = request.getParameter("Meni");
                                                        //For AutoPostBack get the item that you want to remove from the order list
                                                        String remove = request.getParameter("odbrani");

                                                        //For AutoPostBacko get the source
                                                        String pom = request.getParameter("source");

                                                        if ("Meni".equals(pom)) {
                                                            if (odbrano != null) {
                                                                //Add the chosen item in your pending order
                                                                Odbrani.add(odbrano);

                                                            }
                                                        } else if ("odbrani".equals(pom)) {
                                                            if (remove != null) {
                                                                //Remove the chosen item from the pending order
                                                                Odbrani.remove(remove);
                                                            }
                                                        }

                                                        //If you want to modify your order get the parameters and update your old order
                                                        if (izmeni == 1) {
                                                            //Initialize new pending order to avoid duplicates
                                                            Odbrani=new ArrayList<String>();
                                                            
                                                            //Get the items you have ordered
                                                            List<String> lst3 = DataBaseHelper.getLunch(username, IDGroup);
                                                            for (int i = 0; i < lst3.size(); i++) {
                                                                //Add them so you can show them
                                                                Odbrani.add(lst3.get(i));
                                                            }
                                                            //Get the comment
                                                            komentar = DataBaseHelper.getKomentar(username, IDGroup);

                                                            //Add the pending order in your session if it is not already there 
                                                            session.setAttribute("Odbrani", Odbrani);

                                                            //Redirect to make new order
                                                            response.sendRedirect("Naracka.jsp?groupID=" + IDGroup + "&Izmeni=0&Komentar=" + komentar + "&join=true");
                                                        }


                                                        //Save changes you have done to your pending order
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
                                    <input type="hidden" name="OrderUser" value="<%=OrderUser%>"/>

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

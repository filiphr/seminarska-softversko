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
        int izmeni = 0;

        //izmeni=1 -user edits his order, izmeni=0 users creates order
        String izme = (String) request.getParameter("Izmeni");
        if (izme != null && !(izme.isEmpty())) {
            izmeni = 1;
        }
        String komentar = (String) request.getParameter("Komentar");
        if (komentar == null) {
            komentar = new String();
        }
        int flag = 0;
        String flagg = request.getParameter("Flag");
        if (flagg != null) {
            flag = Integer.parseInt(flagg);
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

            function Validate(){
                var x= document.forms["naracka"]["Naracuvac"].value;
                if(x==null || x=="")
                {
                    alert("Изберете за кој сакате да нарачате");
                    return false;   
                }
                var y= document.naracka.odbrani.length

                if(y==1)
                {
                    alert("Изберете јадење");
                    return false;   
                }
            }
            
            function ValidateIzbrisi(){
                var x= document.forms["naracka"]["Naracuvac"].value;
                if(x==null || x=="")
                {
                    alert("Изберете за кој сакате да избришете нарачка");
                    return false;   
                }
            }
            
            function goToMain()
            {
                window.location="MainPage.jsp";
            }
        </script>

        <jsp:include page="../header.jsp"/>
        <div>
            <table>
                <tr>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td>


                    </td>
                </tr>
                <tr>
                    <td>
                        <table border="0">
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
                                                            List<String> UsersNoOrderOrUpdate = new ArrayList<String>();
                                                            List<String> Komentars = new ArrayList<String>();
                                                            String prefMeal = DataBaseHelper.getPreferencesMeal(username);
                                                            if ("false".equals(join)) {
                                                                //Find the users that are not in a group
                                                                UsersNoOrderOrUpdate = new ArrayList<String>();
                                                                List<String> Naracatel = DataBaseHelper.getNarackiByNaracatel(username);
                                                                for (int i = 0; i < Naracatel.size(); i++) {
                                                                    String tmp = Naracatel.get(i);
                                                                    UsersNoOrderOrUpdate.add(tmp);
                                                                }
                                                                UsersNoOrderOrUpdate.addAll(DataBaseHelper.getParticipantBezJadenje());

                                                                UsersNoOrderOrUpdate.remove(username);
                                                            } else if ("true".equals(join)) {
                                                                UsersNoOrderOrUpdate.add(username);
                                                            }

                                                            //Show the users that are not in a group
                                                            for (int i = 0; i < UsersNoOrderOrUpdate.size(); i++) {
                                                                String tmpUsername = UsersNoOrderOrUpdate.get(i);
                                                                String tmpName = DataBaseHelper.getUserIme(tmpUsername);
                                                                String tmpLastName = DataBaseHelper.getUserPrezime(tmpUsername);

                                                        %>
                                                        <option value="<%=tmpUsername%>" <% if (tmpUsername.equals(OrderUser)) {%> selected="selected" <%}%>><%=tmpName%> <%=tmpLastName%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                    <%//If you want to modify your order get the parameters and update your old order
                                                        String tmp = "";
                                                        String tmpKomentari = "";
                                                        if (izmeni == 1) {
                                                            //Initialize new pending order to avoid duplicates
                                                            List<String> Odbranitmp = new ArrayList<String>();

                                                            //Get the items you have ordered
                                                            List<String> lst3 = DataBaseHelper.getLunch(username, IDGroup);
                                                            for (int i = 0; i < lst3.size(); i++) {
                                                                //Add them so you can show them
                                                                Odbranitmp.add(lst3.get(i));
                                                            }
                                                            //Get the comment
                                                            //komentar = DataBaseHelper.getKomentar(username, IDGroup);
                                                            //String tmp = "";
                                                            Komentars = DataBaseHelper.getKomentars(username, IDGroup);
                                                            if (!Odbranitmp.isEmpty()) {


                                                                for (int i = 0; i < Odbranitmp.size(); i++) {
                                                                    tmp += Odbranitmp.get(i) + ";;";
                                                                }
                                                            }
                                                            if (!Komentars.isEmpty()) {


                                                                for (int i = 0; i < Komentars.size(); i++) {
                                                                    tmpKomentari += Komentars.get(i) + ";;";
                                                                }
                                                            }
                                                            flag = 1;
                                                            //Add the pending order in your session if it is not already there %>
                                                    <input type="hidden" name="Odbrani" value="<%=tmp%>"/>
                                                    <input type="hidden" name="Komentari" value="<%=tmpKomentari%>"/>

                                                    <%//Redirect to make new order
                                                            //RequestDispatcher r = request.getRequestDispatcher("Naracka.jsp?groupID=" + IDGroup + "&Izmeni=0&Komentar=" + komentar + "&join=true");
                                                            //r.forward(request, response);
                                                            //response.sendRedirect("Naracka.jsp?groupID=" + IDGroup + "&Izmeni=0&Komentar=" + komentar + "&join=true");
                                                        }%>
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
                                                            List<String> Odbrani = new ArrayList<String>();
                                                            if ("false".equals(join) && flag == 0) {
                                                                String Naracatel = request.getParameter("Naracuvac");
                                                                if (Naracatel != null && !(Naracatel.isEmpty())) {
                                                                    Odbrani = DataBaseHelper.getLunch(Naracatel, IDGroup);
                                                                    Komentars = DataBaseHelper.getKomentars(Naracatel, IDGroup);
                                                                }

                                                            }

                                                            //Get the pending order items from the session
                                                            String Odbranistr = request.getParameter("Odbrani");
                                                            if (Odbranistr == null && !(tmp.isEmpty())) {
                                                                Odbranistr = tmp;
                                                            }

                                                            if (Odbranistr != null && flag == 1) {
                                                                Odbrani = new ArrayList<String>();
                                                                if (!(Odbranistr.isEmpty())) {
                                                                    String[] OdbraniNiza = Odbranistr.split(";;");
                                                                    for (int iter = 0; iter < OdbraniNiza.length; iter++) {
                                                                        Odbrani.add(OdbraniNiza[iter]);
                                                                    }
                                                                }
                                                            }

                                                            String Komentarstr = request.getParameter("Komentari");
                                                            if (Komentarstr == null && !(tmpKomentari.isEmpty())) {
                                                                Komentarstr = tmpKomentari;
                                                            }
                                                            if (Komentarstr != null && flag == 1) {
                                                                Komentars = new ArrayList<String>();
                                                                if (!(Komentarstr.isEmpty())) {
                                                                    String[] KomentariNiza = Komentarstr.split(";;");
                                                                    for (int iter = 0; iter < KomentariNiza.length; iter++) {
                                                                        Komentars.add(KomentariNiza[iter]);
                                                                    }
                                                                    for (int i = Komentars.size() - 1; i < Odbrani.size(); i++) {
                                                                        Komentars.add("");
                                                                    }

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
                                                                    Komentars.add("");
                                                                    flag = 1;
                                                                    //You can only use your own preferences



                                                                }
                                                            }
                                                            if (username.equals(OrderUser)) {
                                                                //Get all the items in the restaurant for this group
                                                                List<String> ItemsInRestaurant = ItemsPrice.get(0);                                                            //Get the prefered meal

                                                                //Check whethee there is a prefered meal and it exists in the current restaurant
                                                                if (prefMeal != null && ItemsInRestaurant.contains(prefMeal) && !Odbrani.contains(prefMeal) && (Odbrani.isEmpty()) && remove == null && "true".equals(join)) {
                                                                    Odbrani.add(prefMeal);
                                                                    Komentars.add(DataBaseHelper.getPreferencesKomentar(username));
                                                                    flag = 1;
                                                                }
                                                            }
                                                            if ("odbrani".equals(pom)) {
                                                                if (remove != null) {
                                                                    flag = 1;
                                                                    //Remove the chosen item from the pending order
                                                                    for (int i = 0; i < Odbrani.size(); i++) {
                                                                        if (Odbrani.get(i).equals(remove)) {
                                                                            Odbrani.remove(i);
                                                                            Komentars.remove(i);
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }



                                                            //Save changes you have done to your pending order
                                                            //session.setAttribute("Odbrani", Odbrani);
                                                            for (int i = 0; i < Odbrani.size(); i++) {
                                                                String s = Odbrani.get(i);
                                                        %>
                                                        <option value="<%=s%>"><%=s%></option>
                                                        <%
                                                            }
                                                            String KomentariT = "";
                                                            String OdbraniT = "";
                                                            if (!Odbrani.isEmpty()) {

                                                                for (int i = 0; i < Odbrani.size(); i++) {
                                                                    OdbraniT += Odbrani.get(i) + ";;";
                                                                }
                                                            }
                                                            if (!Komentars.isEmpty()) {


                                                                for (int i = 0; i < Komentars.size(); i++) {
                                                                    KomentariT += Komentars.get(i) + ";;";
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                    <input type="hidden" name="Odbrani" value="<%=OdbraniT%>"/>
                                                    <input type="hidden" name="Komentari" value="<%=KomentariT%>"/>
                                                    <input type="hidden" name="Flag" value="<%=flag%>"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                    <form method="post" action="Naracka.do" onsubmit="return Validate()">
                                        <input type="hidden" name="groupID" value="<%=IDGroup%>"/>
                                        <input type="hidden" name="OrderUser" value="<%=OrderUser%>"/>
                                        <input type="hidden" name="Odbrani" value="<%=OdbraniT%>"/>
                                        <table>                                       
                                            <%
                                                for (int i = 0; i < Odbrani.size(); i++) {
                                                    String s = Odbrani.get(i);
                                                    String K = "";
                                                    if (!(Komentars.isEmpty()) && i < Komentars.size()) {
                                                        K = Komentars.get(i);
                                                    }
                                                    if ("true".equals(join) && prefMeal != null && prefMeal.equals(Odbrani.get(i)) && K.isEmpty()) {
                                                        K = DataBaseHelper.getPreferencesKomentar(username);
                                                    }

                                            %>
                                            <tr>
                                                <td>
                                                    Коментар за <%=s%>: <input type="text" name="Komentar<%=i%>" value="<%=K%>"/>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                            <tr>
                                                <td>
                                                    <input type="submit" name="Naracaj" value="Naracaj"/>
                                                </td>
                                                <td>
                                                    <input type="button" value="Врати се назад" onclick="goToMain()"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                    <% if ("false".equals(join)) {%>
                                    <form action="IzbrisiNaracka.do" method="post" onsubmit="return ValidateIzbrisi()">
                                        <input type="hidden" name="Naracuvac" value="<%=OrderUser%>"/>
                                        <input type="hidden" name="IDGrupa" value="<%=IDGroup%>"/>
                                        <input type="submit" value="Izbrisi Naracka"/>
                                    </form>
                                    <% }%>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>

                    </td>
                </tr>
            </table>
        </div>
    </body>

</html>

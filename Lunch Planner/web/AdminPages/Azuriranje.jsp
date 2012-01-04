<%-- 
    Document   : Azuriranje
    Created on : Jan 3, 2012, 2:19:15 PM
    Author     : pc
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <input type="hidden" name="selectedAccordion" value="0"/>
    <% int selectedAccordion = 0;
        if (request.getParameter("selectedAccordion") != null) {
            selectedAccordion = Integer.parseInt(request.getParameter("selectedAccordion"));
        }
    %>
    <% String usrNameDelete = "";
        if (request.getParameter("usrNameDelete") != null) {
            usrNameDelete = request.getParameter("usrNameDelete");
        }
    %>
    <% String resNameDeleteItem = "";
        if (request.getParameter("resNameDeleteItem") != null) {
            resNameDeleteItem = request.getParameter("resNameDeleteItem");
        }
    %>
    <script lang="JavaScript" src="accordion-js.js"></script>
    <script lang="JavaScript" src="submiting_funcions.js"></script>
    <link rel="stylesheet" type="text/css" href="tabs-accordion.css">

    <head>
        <title>Ажурирање на податоци</title>
    </head>
    
    <body onpageshow="runAccordion(<%=selectedAccordion%>);">
        
    <div id="AccordionContainer" class="AccordionContainer">

            <div onclick="runAccordion(1);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Додавање на корисник
                </div>
            </div>
            <div id="Accordion1Content" class="AccordionContent">

                <form method="POST" action="AddUser.do">
                    <table>
                        <tr><td>Корисничко име</td><td><input type="text" name="Username"/></td></tr>
                        <tr><td>Име</td><td><input type="text" name="Ime"/></td></tr>
                        <tr><td>Презиме</td><td><input type="text" name="Prezime"/></td></tr>
                        <tr><td>Лозинка</td><td><input type="text" name="Lozinka"/></td></tr>
                        <tr><td>e-mail</td><td><input type="text" name="email"/></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Додади"/></td></tr>
                    </table>
                </form>

            </div>

            <div onclick="runAccordion(2);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Менување на корисник
                </div>
            </div>
            <div id="Accordion2Content" class="AccordionContent">

                <table>
                    <form id="usrUpdateForm" action="" method="POST"/>
                    <tr><td colspan="2"><select name="Username" size="1" onclick=""submitUserUpdate()">
                                <%
                                    List<String> iminja1 = DataBaseHelper.getAllUsernames();
                                    for (int i = 0; i < iminja1.size(); i++) {
                                        String s = iminja1.get(i);
                                %>
                                <option value="s"><%=s%></option>
                                <%
                                    }
                                %>
                            </select></td></tr>
                    </form>
                    <form method="POST" action="UpdateUser.do">
                        <tr><td>Име</td><td><input type="text" name="Ime"/></td></tr>
                        <tr><td>Презиме</td><td><input type="text" name="Prezime"/></td></tr>
                        <tr><td>Лозинка</td><td><input type="text" name="Lozinka"/></td></tr>
                        <tr><td>e-mail</td><td><input type="text" name="email"/></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Промени"/></td></tr>
                </table>
                </form>
            </div>
            <div onclick="runAccordion(3);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Бришење на корисник
                </div>
            </div>
            <div id="Accordion3Content" class="AccordionContent">
                <form method="POST" action="DeleteUser.do">
                    <select name="Username" size="1" onclick="submitDeleteUser()">
                        <%
                            List<String> iminja2 = DataBaseHelper.getAllUsernames();
                            for (int i = 0; i < iminja2.size(); i++) {
                                String s = iminja2.get(i);
                        %>
                        <option value="<%=s%>"><%=s%></option>
                        <%
                            }
                        %>
                    </select></br>
                    Име и презиме:<%=DataBaseHelper.getUserIme(usrNameDelete)%> <%=DataBaseHelper.getUserPrezime(usrNameDelete)%> </br>
                    <input type="submit" value="Избриши"/>
                </form>

            </div>
            <div onclick="runAccordion(4);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Додавање на ресторант
                </div>
            </div>
            <div id="Accordion4Content" class="AccordionContent">
                <form method="POST" action="AddRestaurant.do">
                    <table>
                        <tr><td>Име</td><td><input type="text" name="Ime"/></td></tr>
                        <tr><td>Презиме</td><td><input type="text" name="Adresa"/></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Додади"/></td></tr>
                    </table>
                </form>

            </div>
            <div onclick="runAccordion(5);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Бришење ресторант
                </div>
            </div>
            <div id="Accordion5Content" class="AccordionContent">
                <form method="POST" action="DeleteRestaurant.do">
                    <table>
                        <tr><td colspan="2"><select name="Ime" size="1">
                                    <%
                                        List<String> lst1 = DataBaseHelper.getAllRestaurantNames();
                                        for (int i = 0; i < lst1.size(); i++) {
                                            String s = lst1.get(i);
                                    %>
                                    <option value="<%=s%>"><%=s%></option>
                                    <%
                                        }
                                    %>
                                </select></td></tr>
                        <td colspan="2"><input type="submit" value="Избриши"/></td></tr>
                    </table>
                </form>

            </div>
            <div onclick="runAccordion(6);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Додавање ставка во менито
                </div>
            </div>
            <div id="Accordion6Content" class="AccordionContent">
                <form method="POST" action="AddUser.do">
                    <table>
                        <tr><td colspan="2"><select name="Ime" size="1">
                                    <%
                                        List<String> lst2 = DataBaseHelper.getAllRestaurantNames();
                                        for (int i = 0; i < lst2.size(); i++) {
                                            String s = lst2.get(i);
                                    %>
                                    <option value="<%=s%>"><%=s%></option>
                                    <%
                                        }
                                    %>
                                </select></td></tr>
                        <tr><td>Име на ставка</td><td><input type="text" name="ImeStavka"/></td></tr>
                        <tr><td>Цена на ставка</td><td><input type="text" name="CenaStavka"/></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Додади"/></td></tr>
                    </table>
                </form>

            </div>
            <div onclick="runAccordion(7);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Бришење ставка од менито
                </div>
            </div>
            <div id="Accordion7Content" class="AccordionContent">

                <form method="POST" action="AddUser.do">
                    <table>
                        <tr><td><select name="Ime" size="1">
                                    <%
                                        List<String> lst3 = DataBaseHelper.getAllRestaurantNames();
                                        for (int i = 0; i < lst3.size(); i++) {
                                            String s = lst3.get(i);
                                    %>
                                    <option value="<%=s%>"><%=s%></option>
                                    <%
                                        }
                                    %>
                                </select></td></tr>
                        <tr><td><select name="Meni" size="1">
                                    <%
                                        List<String> lst4 = DataBaseHelper.getAllMenuItems(resNameDeleteItem);
                                        for (int i = 0; i < lst4.size(); i++) {
                                            String s = lst4.get(i);
                                    %>
                                    <option value="<%=s%>"><%=s%></option>
                                    <%
                                        }
                                    %>
                                </select></td></tr>
                        <tr><td><input type="submit" value="Избриши"/></td></tr>
                    </table>
                </form>

            </div>
        </div>
    </body>
    <h1></h1>
</body>
</html>

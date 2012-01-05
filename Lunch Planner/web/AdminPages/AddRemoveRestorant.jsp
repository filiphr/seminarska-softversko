<%-- 
    Document   : AddRestorant
    Created on : Jan 5, 2012, 12:07:42 PM
    Author     : Home
--%>

<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <form method="POST" action="AddRestaurant.do">
                <table>
                    <tr><th width="300px" colspan="2">Внесување на ресторант</th></tr>
                    <tr><td>Име</td><td><input type="text" name="ImeDodadi"/></td></tr>
                    <tr><td>Adresa</td><td><input type="text" name="AdresaDodadi"/></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Додади"/></td></tr>
                </table>
            </form>
        </div>
        <div>
            <form method="POST" action="DeleteRestaurant.do">
                <table>
                    <tr><th width="300px">Бришење ресторант</th></tr>
                    <tr><td><select name="ImeIzbrisi" size="1">
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
                    <td><input type="submit" value="Избриши"/></td></tr>
                </table>
            </form>
        </div>
    </body>
</html>

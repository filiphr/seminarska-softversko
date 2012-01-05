<%-- 
    Document   : AddRemoveItem
    Created on : Jan 5, 2012, 12:13:54 PM
    Author     : Home
--%>

<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <% String resNameDeleteItem = "";
        if (request.getParameter("resNameDeleteItem") != null) {
            resNameDeleteItem = request.getParameter("resNameDeleteItem");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aжурирање на мени на ресторантите</title>
        <script type="text/javascript">
            function validateAddMenuItemForm()
            {
                var x = document.forms["AddMenuItemForm"]["ImeDodadi"].value
                if(x==null || x=="")
                {
                    alert("Изберете ресторант");
                    return false;
                }
                x = document.forms["AddMenuItemForm"]["ImeStavka"].value
                if(x==null || x=="")
                {
                    alert("Пополнете го името на ставката");
                    return false;
                }
                x=document.forms["AddMenuItemForm"]["CenaStavka"].value
                if(x==null || x=="")
                {
                    alert("Пополнете ја цената на ставката");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <div>
            <form name="AddMenuItemForm" method="POST" action="AddMenuItem.do" onsubmit="return validateAddMenuItemForm()">
                <table>
                    <tr><th width="300px" colspan="2">Додавање ставка во менито</th></tr>
                    <tr><td width="200px" colspan="2"><select name="ImeDodadi" size="1">
                                <option value="">-избери ресторант-</option>
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


        <div>
            <form method="post" action="">  
                <table>
                    <tr><th width="300px">Бришење на ставка од менито</th></tr>
                    <tr><td width="200" ><select name="resNameDeleteItem" size="1" onchange="submit()">
                                <option value="">-избери ресторант-</option>
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
                </table>
            </form>
            <form method="POST" action="DeleteMenuItem.do">
                <input type="hidden" name="ImeIzbrisi" value="<%=resNameDeleteItem%>"/>
                <table>
                    <tr><td width="200px" ><select  name="Meni" size="1">
                                <option value="">-избери ставка-</option>
                                <%
                                    List<List<String>> lst4 = DataBaseHelper.getAllMenuItemsAndPrice(resNameDeleteItem);
                                    //List<String> lst4 = DataBaseHelper.getAllMenuItems(resNameDeleteItem);
                                    for (int i = 0; i < lst4.get(0).size(); i++) {
                                        String s = lst4.get(0).get(i);
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
    </body>
</html>

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
        <title>Ажурирање на листа на ресторанти</title>
        <link rel="stylesheet" type="text/css" href="NazadCss.css" />
        <script type="text/javascript">
            function validateAddRestorantForm()
            {
                var x=document.forms["AddRestorantForm"]["ImeDodadi"].value;
                if (x==null || x=="")
                {
                    alert("Пополнете го името");
                    return false;
                }
                x=document.forms["AddRestorantForm"]["AdresaDodadi"].value;
                if (x==null || x=="")
                {
                    alert("Пополнете ја адресата");
                    return false;
                }
                x=document.forms["AddRestorantForm"]["TelefonDodadi"].value;
                if (x==null || x=="")
                {
                    alert("Попонете го телефонот");
                    return false;
                }
            }
            function validateRemoveRestorantForm()
            {
                var x=document.forms["RemoveRestorantForm"]["ImeIzbrisi"].value;
                if (x==null || x=="")
                {
                    alert("Изберете ресторант");
                    return false;
                }
            }
            
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <form name="AddRestorantForm" method="POST" action="AddRestaurant.do" onsubmit="return validateAddRestorantForm()">
                <table>
                    <tr><th width="300px" colspan="2">Внесување на ресторант</th></tr>
                    <tr><td>Име</td><td><input type="text" name="ImeDodadi"/></td></tr>
                    <tr><td>Adresa</td><td><input type="text" name="AdresaDodadi"/></td></tr>
                    <tr><td>Телефон</td><td><input type="text" name="TelefonDodadi"/></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Додади"/></td></tr>
                </table>
            </form>
            <form name="RemoveRestorantForm" method="POST" action="RemoveRestorant.do" onsubmit="return validateRemoveRestorantForm()">
                <table>
                    <tr><th width="300px">Бришење ресторант</th></tr>
                    <tr><td><select name="ImeIzbrisi" size="1">
                                <option value="">-избери ресторант-</option>
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
            <p><a href="Azuriranje.jsp"><b>Назад</b></a></p>
        </div>
    </body>
</html>

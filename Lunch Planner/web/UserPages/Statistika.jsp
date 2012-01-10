<%-- 
    Document   : Statistika
    Created on : Jan 7, 2012, 8:32:58 PM
    Author     : Home
--%>

<%@page import="java.util.List"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String userID = "";
        synchronized (session) {
            userID = (String) request.getSession().getAttribute("username");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Корисничка статистика</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <table>

                <%
                    List<List<String>> lst = DataBaseHelper.getVremeRestoranStavkaOdArhivirani(userID);
                    if (!lst.isEmpty()) {
                %>
                <tr>
                    <th width="25%">Ресторант</th>
                    <th width="25%">Јадење</th>
                    <th width="25%">Датум</th>
                </tr> 
                <%
                    for (int i = (lst.get(0).size() - 1); i >= 0 && i > (lst.get(0).size() - 20); i--) {
                        String vreme = lst.get(0).get(i);
                        String restorant = lst.get(1).get(i);
                        String stavka = lst.get(2).get(i);
                %>
                <tr>
                    <td>
                        <%= restorant%>
                    </td>
                    <td>
                        <%= stavka%>
                    </td>
                    <td>
                        <%= vreme%>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td coslpan="3">
                        Немате корисничка исотрија  
                    </td>
                </tr>
                <%                    }
                %>
            </table>
        </div>
    </body>
</html>

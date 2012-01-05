<%-- 
    Document   : UpdateUser
    Created on : Jan 5, 2012, 11:45:27 AM
    Author     : Home
--%>

<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <% String usrNameUpdate = "";
        if (request.getParameter("usrNameUpdate") != null) {
            usrNameUpdate = request.getParameter("usrNameUpdate");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form id="usrUpdateForm" action="" method="post">
            <table>
                <tr><td colspan="2"><select name="usrNameUpdate" size="1"  onchange="submit()" >
                            <option value="">-селектирај корисник-</option>
                            <%
                                List<String> iminja1 = DataBaseHelper.getAllUsernames();
                                for (int i = 0; i < iminja1.size(); i++) {
                                    String s = iminja1.get(i);
                            %>
                            <option value="<%=s%>"
                                    <% if (s.equals(usrNameUpdate)) {
                                            out.write("selected=\"true\"");
                                        }%>
                                    >
                                <%=s%></option>
                                <%
                                    }
                                %>
                        </select></td></tr>
            </table>
        </form>
        <form method="POST" action="UpdateUser.do">
            <input type="hidden" name="Username"value="<%=usrNameUpdate%>"/>
            <table>
                <tr><td>Име</td>
                    <td><input type="text" name="Ime" value="<%=DataBaseHelper.getUserIme(usrNameUpdate)%>"/></td></tr>
                <tr><td>Презиме</td>
                    <td><input type="text" name="Prezime" value="<%=DataBaseHelper.getUserPrezime(usrNameUpdate)%>"/></td></tr>
                <tr><td>Лозинка</td>
                    <td><input type="text" name="Lozinka" value="<%=DataBaseHelper.getUserLozinka(usrNameUpdate)%>"/></td></tr>
                <tr><td>e-mail</td><td><input type="text" name="email" value="<%=DataBaseHelper.getUserEmail(usrNameUpdate)%>"/></td></tr>
                <tr><td colspan="2"><input type="submit" value="Промени"/></td></tr>
            </table>
        </form>
    </body>
</html>

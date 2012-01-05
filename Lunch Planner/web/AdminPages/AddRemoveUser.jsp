<%-- 
    Document   : AddUser
    Created on : Jan 5, 2012, 11:44:55 AM
    Author     : Home
--%>

<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String usrNameDelete = "";
        if (request.getParameter("usrNameDelete") != null) {
            usrNameDelete = request.getParameter("usrNameDelete");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <div>
            <form method="post" action="AddUser.do">
                <table>
                    <tr><th width="300px" colspan="2">Внесување на корисник</th></tr>
                    <tr><td>Корисничко име</td><td><input type="text" name="Username"/></td></tr>
                    <tr><td>Име</td><td><input type="text" name="Ime"/></td></tr>
                    <tr><td>Презиме</td><td><input type="text" name="Prezime"/></td></tr>
                    <tr><td>Лозинка</td><td><input type="text" name="Lozinka"/></td></tr>
                    <tr><td>e-mail</td><td><input type="text" name="email"/></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Додади"/></td></tr>
                </table>
            </form>
        </div>


        <div>
            <form method="post" action="" id="usrDeleteForm">
                <table>
                    <tr><th colspan="2" width="300px">Бришење на кориснки</th></tr>
                    <tr><td colspan="2" ><select name="usrNameDelete" size="1" onchange="submit()" >
                                <option value="">-селектирај корисник-</option>
                                <%
                                    List<String> iminja2 = DataBaseHelper.getAllUsernames();
                                    for (int i = 0; i < iminja2.size(); i++) {
                                        String s = iminja2.get(i);
                                %>
                                <option value="<%=s%>"
                                        <% if (s.equals(usrNameDelete)) {
                                                out.write("selected=\"true\"");
                                            }%>
                                        ><%=s%></option>
                                <%
                                    }
                                %>
                            </select></td></tr>
                </table>
            </form>
            <form method="POST" action="DeleteUser.do">
                <input type="hidden" name="UsernameDelete" value="<%=usrNameDelete%>"/>
                <table>
                    <tr><td width="150px">Име и презиме:</td><td><%=DataBaseHelper.getUserIme(usrNameDelete)%> <%=DataBaseHelper.getUserPrezime(usrNameDelete)%> </td></tr>
                    <tr><td colspan="2"><input type="submit" value="Избриши"/></td></tr>
                </table>
            </form>
        </div>
    </body>
</html>

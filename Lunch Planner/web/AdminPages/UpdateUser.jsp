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

    <%
        String userID = (String) session.getAttribute("username");
        String usrNameUpdate = "";
        if (request.getParameter("usrNameUpdate") != null) {
            usrNameUpdate = request.getParameter("usrNameUpdate");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ажурирање податоци за корисник</title>
        <link rel="stylesheet" type="text/css" href="NazadCss.css" />
        <script type="text/javascript">
            function validateUpdateUserForm()
            {
                var x=document.forms["UpdateUserForm"]["Username"].value;
                if (x==null || x=="")
                {
                    alert("Изберете корисник");
                    return false;
                }
                x=document.forms["UpdateUserForm"]["Ime"].value;
                if (x==null || x=="")
                {
                    alert("Името не смее да биде празно");
                    return false;
                }
                x=document.forms["UpdateUserForm"]["Prezime"].value;
                if (x==null || x=="")
                {
                    alert("Презимето не смее да биде празно");
                    return false;
                }
                x=document.forms["UpdateUserForm"]["Lozinka"].value;
                if (x==null || x=="")
                {
                    alert("Лозинката не смее да биде празна");
                    return false;
                }
                x=document.forms["UpdateUserForm"]["email"].value;
                if (x==null || x=="")
                {
                    alert("е-маилот не смее да биде празен");
                    return false;
                }
                x=document.forms["UpdateUserForm"]["email"].value;
                var atpos=x.indexOf("@");
                var dotpos=x.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
                {
                    alert("Невалиден е-маил");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div>
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
            <form method="POST" name="UpdateUserForm" action="UpdateUser.do" onsubmit="return validateUpdateUserForm()" >
                <input type="hidden" name="Username"value="<%=usrNameUpdate%>"/>
                <table>
                    <tr><td>Име</td>
                        <td><input type="text" name="Ime" value="<%=DataBaseHelper.getUserIme(usrNameUpdate)%>"/></td></tr>
                    <tr><td>Презиме</td>
                        <td><input type="text" name="Prezime" value="<%=DataBaseHelper.getUserPrezime(usrNameUpdate)%>"/></td></tr>
                    <tr><td>Лозинка</td>
                        <td><input type="text" name="Lozinka" value="<%=DataBaseHelper.getUserLozinka(usrNameUpdate)%>"/></td></tr>
                    <tr><td>e-mail</td><td><input type="text" name="email" value="<%=DataBaseHelper.getUserEmail(usrNameUpdate)%>"/></td></tr>
                            <% if (!usrNameUpdate.equals(userID)) {%>
                    <tr><td>Администраторски привилегии</td><td><input type="checkbox" name="Administrator" 
                                                                       <% if (DataBaseHelper.isAdministrator(usrNameUpdate)) {
                                                                               out.write("checked");
                                                                           }%>/></td></tr>
                            <%}%>
                    <tr><td colspan="2"><input type="submit" value="Промени"/></td></tr>
                </table>
            </form>
            <p><a href="Azuriranje.jsp"><b>Назад</b></a></p>
        </div>
    </body>
</html>

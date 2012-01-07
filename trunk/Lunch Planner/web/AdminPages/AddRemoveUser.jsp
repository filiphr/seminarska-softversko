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
        <title>Ажурирање на листата на корисници</title>
        <link rel="stylesheet" type="text/css" href="NazadCss.css" />
        <script type="text/javascript" >
            function validateAddUserForm()
            {
                var x=document.forms["AddUserForm"]["Username"].value;
                if (x==null || x=="")
                {
                    alert("Пополнете го корисничкото име");
                    return false;
                }
                x=document.forms["AddUserForm"]["Ime"].value;
                if (x==null || x=="")
                {
                    alert("Пополнете го името");
                    return false;
                }
                x=document.forms["AddUserForm"]["Prezime"].value;
                if (x==null || x=="")
                {
                    alert("Пополнете го презимето");
                    return false;
                }
                x=document.forms["AddUserForm"]["Lozinka"].value;
                if (x==null || x=="")
                {
                    alert("Пополнете ја лозинката");
                    return false;
                }
                x=document.forms["AddUserForm"]["email"].value;
                if (x==null || x=="")
                {
                    alert("Пополнете го е-маилот");
                    return false;
                }
                x=document.forms["AddUserForm"]["email"].value;
                var atpos=x.indexOf("@");
                var dotpos=x.lastIndexOf(".");
                if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
                {
                    alert("Невалиден е-маил");
                    return false;
                }
            }
            function validateDeleteUserForm()
            {
                var x=document.forms["DeleteUserForm"]["Username"].value;
                if (x==null || x=="")
                {
                    alert("Изберете корисничко име");
                    return false;
                }
            }
            
        </script>
    </head>
    <body>

<jsp:include page="../header.jsp"/>
        <div>
            <form name="AddUserForm" method="post" action="AddUser.do" onsubmit="return validateAddUserForm()">
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
            <form name="DeleteUserForm" method="POST" action="DeleteUser.do" onsubmit="return validateDeleteUserForm()">
                <input type="hidden" name="UsernameDelete" value="<%=usrNameDelete%>"/>
                <table>
                    <tr><td width="150px">Име и презиме:</td><td><%=DataBaseHelper.getUserIme(usrNameDelete)%> <%=DataBaseHelper.getUserPrezime(usrNameDelete)%> </td></tr>
                    <tr><td colspan="2"><input type="submit" value="Избриши"/></td></tr>
                </table>
            </form>
        </div>
        <p><a href="Azuriranje.jsp"><b>Назад</b></a></p>
    </body>
</html>

<%-- 
    Document   : Main
    Created on : 04-Jan-2012, 11:16:42
    Author     : Filip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        

    </head>
    <body>
        <h1>Hello World Main!</h1>

        <form method="POST" action="">
            <table>
                <tr class="header">
                    <td>

                    </td>
                </tr>
                <tr class="menu" >
                    <td>
                        

                    </td>
                </tr>
                <tr class="body" >
                    <td>
                        <%=request.getParameter("username")%><br/>
                        <%=request.getParameter("password")%><br/>
                        <%=request.getSession().getAttribute("username")%><br/>
                        <%Cookie[] cookies = request.getCookies();
                            for (int i = 0; i < cookies.length; i++) {
                                Cookie c = cookies[i];
                                if ("username".equals(c.getName())) {
                        %>
                        <%=c.getValue()%><br/>
                        <%
                            }
                            if ("password".equals(c.getName())) {
                        %>
                        <%=c.getValue()%><br/>
                        <%
                                }
                            }                        
                        %>
                    </td>

                </tr>
                <tr class="footer" >
                    <td>

                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

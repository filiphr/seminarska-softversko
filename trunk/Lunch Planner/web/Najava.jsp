<%-- 
    Document   : Najava
    Created on : 03-Jan-2012, 15:16:59
    Author     : Filip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Najava</title>
        <link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/HeaderCSS.css"/>
    </head>
    <body>
        <table id="header"> 
            <tr>
                <td rowspan="3">
                    <img src="/Lunch_Planner/nca-logo-home.GIF"/>
                </td>
            </tr>
        </table>
        <form method="POST" action="LoginServlet.do">
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
                        <table>
                            <tr>
                                <td>
                                    Корисничко име:
                                </td>
                                <td>
                                    <input type="text" name="username" >
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Лозинка:
                                </td>
                                <td>
                                    <input type="password" name="password"  />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    
                                </td>
                                <td>
                                    <input type="checkbox" name="remember" value="selected"/>Запамети ме
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="button" value="Потсети ме" />
                                </td>
                                <td>
                                    <input type="submit" value="Логирај се" />
                                </td>
                            </tr>
                        </table>
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

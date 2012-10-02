<%-- 
    Document   : Najava
    Created on : 03-Jan-2012, 15:16:59
    Author     : Filip
--%>

<%@page import="prediction.BuildC45forAllEmployee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
    synchronized(application){
                      
                      BuildC45forAllEmployee bC45 = (BuildC45forAllEmployee) application.getAttribute("PredikcijaRestorani");
                      if (bC45==null) {
                          application.setAttribute("DatumObuka", DataBase.DataBaseHelper.getFirstDate());
                          application.setAttribute("DatumUser", DataBase.DataBaseHelper.getFirstDate());
                          bC45 = prediction.SetC45.set(DataBase.DataBaseHelper.getFirstDate(), DataBase.DataBaseHelper.getFirstDate());
                          application.setAttribute("PredikcijaRestorani", bC45);
                      }
                }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Најава</title>
        <link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/HeaderCSS.css"/>
        <link rel="stylesheet" type="text/css" href="/Lunch_Planner/CSS/GlavnaUreduvackaCSS.css"/>
    </head>
    <body>

        <table class="header"> 
            <tr>
                <td rowspan="3">
                    <img src="/Lunch_Planner/nca-logo-home.GIF"/>
                </td>
            </tr>
        </table>
        <div> 
            <form method="POST" action="LoginServlet.do">
                <table class="najavnaTabela">
                    <tr>
                        <td>

                        </td>
                    </tr>
                    <tr>
                        <td>

                        </td>
                    </tr>
                    <tr>
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
                    <tr>
                        <td>

                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

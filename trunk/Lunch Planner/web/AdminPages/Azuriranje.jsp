<%-- 
    Document   : Azuriranje
    Created on : Jan 3, 2012, 2:19:15 PM
    Author     : pc
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <script lang="JavaScript" src="accordion-js.js"></script>
    <link rel="stylesheet" type="text/css" href="tabs-accordion.css">

    <head>
        <title>Ажурирање на податоци</title>
    </head>
    <body>
        <div id="AccordionContainer" class="AccordionContainer">

            <div onclick="runAccordion(1);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Додавање на корисник
                </div>
            </div>
            <div id="Accordion1Content" class="AccordionContent">
                <form method="POST" action="AddUser.do">
                    Корисничко име<input type="text" name="User"/>
                    Име<input type="text" name="Ime"/>
                    Презиме<input type="text" name="Prezime"/>
                    Лозинка<input type="text" name="Lozinka"/>
                </form>

            </div>

            <div onclick="runAccordion(2);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Менување на корисник
                </div>
            </div>
            <div id="Accordion2Content" class="AccordionContent">
                <form method="POST" action="UpdateUser.do">
                    <select name="username" size="1">
                        <%
                            List<String> iminja1 = DataBaseHelper.getAllUsernames();
                            for (int i = 0; i < iminja1.size(); i++) {
                                String s = iminja1.get(i);
                        %>
                        <option value="s"><%=s%></option>
                        <%
                            }
                        %>
                    </select>
                    Име<input type="text" name="Ime"/></br>
                    Презиме<input type="text" name="Prezime"/></br>
                    Лозинка<input type="text" name="Lozinka"/></br>
                </form>
            </div>
            <div onclick="runAccordion(5);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Бришење на корисник
                </div>
            </div>
            <div id="Accordion5Content" class="AccordionContent">
                <form method="POST" action="DeleteUser.do">
                    <select name="username" size="1">
                        <%
                            List<String> iminja2 = DataBaseHelper.getAllUsernames();
                            for (int i = 0; i < iminja2.size(); i++) {
                                String s = iminja2.get(i);
                        %>
                        <option value="s"><%=s%></option>
                        <%
                            }
                        %>
                    </select>
                </form>

            </div>
            <div onclick="runAccordion(3);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Додавање на ресторант
                </div>
            </div>
            <div id="Accordion3Content" class="AccordionContent">
                <form method="POST" action="AddUser.do">

                </form>

            </div>
            <div onclick="runAccordion(4);">
                <div class="AccordionTitle" onselectstart="return false;">
                    Додавање на корисник
                </div>
            </div>
            <div id="Accordion4Content" class="AccordionContent">
                <form method="POST" action="AddUser.do">

                </form>

            </div>
        </div>
    </body>
    <h1></h1>
</body>
</html>

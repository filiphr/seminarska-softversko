<%-- 
    Document   : Preferences
    Created on : Jan 6, 2012, 10:20:07 AM
    Author     : Home
--%>

<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String resNamePrefItem = "";
        if (request.getParameter("resNamePrefItem") != null) {
            resNamePrefItem = request.getParameter("resNamePrefItem");
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Кориснички преференци</title>
        <script type="text/javascript">
            function validatePreferenceForm()
            {
                var x = document.forms["PreferenceForm"]["Vreme"].value;
                if(x!=""){
                    var pat = /([01]\d|2[0-3]):[0-5]\d/;
                    if(!pat.test(x))
                    {
                        alert("Формат за време ЧЧ:ММ");
                        return false;
                    }
                }
            }
            function validateParticipantiForm()
            {
                var x = document.forms["ParticipantiForm"]["Participanti"].value;
                if(x==null)
                {
                    alert("Изберете бар еден партиципант");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <form action="" method="post">
            <table>
                <tr><th colspan="2">Преферирани опции</th></tr>
                <tr><td colspan="2" width="300px">
                        <select name="resNamePrefItem" size="1" onchange="submit()">
                            <option value="">-избери ресторант-</option>
                            <%
                                List<String> lst3 = DataBaseHelper.getAllRestaurantNames();
                                for (int i = 0; i < lst3.size(); i++) {
                                    String s = lst3.get(i);
                            %>
                            <option value="<%=s%>"
                                    <% if (s.equals(resNamePrefItem)) {
                                            out.write("selected=\"true\"");
                                        }%>
                                    ><%=s%></option>
                            <%
                                }
                            %>
                        </select></td></tr>
            </table>
        </form>
        <form name="PreferenceForm" method="post" action="" onsubmit="return validatePreferenceForm()">
            <input type="hidden" name="ImeRestorant" value="<%=resNamePrefItem%>"/>
            <table>
                <tr><td colspan="2" width="300px" ><select  name="Meni" size="1">
                            <option value="">-избери ставка-</option>
                            <%
                                List<List<String>> lst4 = DataBaseHelper.getAllMenuItemsAndPrice(resNamePrefItem);
                                for (int i = 0; i < lst4.get(0).size(); i++) {
                                    String s = lst4.get(0).get(i);
                            %>
                            <option value="<%=s%>"><%=s%></option>
                            <%
                                }
                            %>
                        </select></td></tr>
                <tr><td>Време:</td><td><input type="text" name="Vreme"/></td></tr>
                <tr><td>Коментар:</td><td><input type="text" name="Komentar"/></tr>
                <tr><td colspan="2"><input type="submit"/></td></tr>
            </table>
        </form>
        <br/><br>
        <form name="ParticipantiForm" onsubmit="return validateParticipantiForm()">
            <table> 
                <tr><th>Омилени партиципанти</th></tr>
                <tr><td width="300px"> <select size="8" name="Participanti" multiple="multiple">
                            <% List<List<String>> lst = DataBaseHelper.getAllNamesSNamesUsersEmailsPass();
                                for (int i = 0; i < lst.get(0).size(); i++) {
                                    String name = lst.get(0).get(i);
                                    String sname = lst.get(1).get(i);
                                    String user = lst.get(2).get(i);
                            %>
                            <option value="<%=user%>" ><%=name%> <%=sname%></option>
                            <%
                                }
                            %></select></td>
                    <td><select size="8" disabled="disabled">
                            <%
                                //SMENI GO!
                                List<String> lst2 = DataBaseHelper.getPreferencesParticipant("mdocevski");
                                for (int i = 0; i < lst2.size(); i++) {
                                    String s = lst2.get(i);
                            %>
                            <option><%=s%></option>
                            <%
                                }
                            %>
                        </select>
                </tr>
                <tr><td><input type="submit"/></td></tr>
            </table>
        </form>
        <p><a href="../AdminPages/Azuriranje.jsp"><b>Назад</b></a></p>
    </body>
</html>
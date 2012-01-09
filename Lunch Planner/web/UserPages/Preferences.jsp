<%-- 
    Document   : Preferences
    Created on : Jan 6, 2012, 10:20:07 AM
    Author     : Home
--%>

<%@page import="org.apache.catalina.Session"%>
<%@page import="com.sun.org.apache.regexp.internal.RESyntaxException"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String userID = "";
        synchronized (session) {
            userID = (String) session.getAttribute("username");
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
            function validateParticipantiDodadiForm()
            {
                var x = document.forms["ParticipantiDodadiForm"]["ParticipantiDodadi"].value;
                if(x==null)
                {
                    alert("Изберете бар еден партиципант");
                    return false;
                }
            }
            function submitDodadi()
            {
                var myForm = document.forms["ParticipantiDodadiForm"];
                myForm.submit();
            }
            function submitOdzemi()
            {
                var myForm = document.forms["ParticipantiOdzemiForm"];
                myForm.submit();
            }
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <form method="post" name="PreferenceForm" action="Preferences.do" onsubmit="return validatePreferenceForm()">
                <table class="izbornaTabela">
                    <tr>
                        <th colspan="3">Преферирани опции</th>
                    </tr>
                    <tr>
                        <td colspan="2" width="300px">
                            <select name="ImeRestorant" size="1">
                                <option value="">-избери ресторант-</option>
                                <%
                                    List<String> lst3 = DataBaseHelper.getAllRestaurantNames();
                                    for (int i = 0; i < lst3.size(); i++) {
                                        String s = lst3.get(i);
                                %>
                                <option value="<%=s%>"
                                        <% if (s.equals(DataBaseHelper.getPreferencesRestoran(userID))) {
                                                out.write("selected=\"true\"");
                                            }%>
                                        ><%=s%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                        <td><a href="ClearPreference.do?what=restorant">Избриши</a></td>
                    </tr>
                    <tr>
                        <td colspan="2" width="300px" >
                            <select  name="Meni" size="1">
                                <option value="">-избери ставка-</option>
                                <%
                                    List<String> lst4 = DataBaseHelper.getAllItems();
                                    for (int i = 0; i < lst4.size(); i++) {
                                        String s = lst4.get(i);
                                %>
                                <option value="<%=s%>"
                                        <%
                                            if (s.equals(DataBaseHelper.getPreferencesMeal(userID))) {
                                                out.write("selected=\"true\"");
                                            }
                                        %>
                                        > <%=s%></option>
                                <%


                                    }
                                %>
                            </select>
                        </td>
                        <td><a href="ClearPreference.do?what=stavka">Избриши</a></td>
                    </tr>
                    <tr>
                        <td>Време:</td>
                        <td><input type="text" value="<%= DataBaseHelper.getPreferencesHour(userID)%>" name="Vreme"/></td>
                        <td><a href="ClearPreference.do?what=vreme">Избриши</a></td>
                    </tr>
                    <tr>
                        <td>Коментар:</td>
                        <td><input type="text" value="<%= DataBaseHelper.getPreferencesKomentar(userID)%>" name="Komentar"/></td>
                        <td><a href="ClearPreference.do?what=komentar">Избриши</a></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input value="Смени" type="submit"/></td>
                    </tr>
                </table>
            </form>

            <br/><br/>

            <table class="izbornaTabela"> 
                <tr><th width="300px" colspan="2">Омилени партиципанти</th></tr>
                <tr>
                    <td width="150px"> 
                        <form method="post" name="ParticipantiDodadiForm" action="ParticipantiDodadi.do" onsubmit="return validateParticipantiDodadiForm()">
                            <select size="8" name="ParticipantiDodadi" multiple="multiple">
                                <% List<List<String>> lst = DataBaseHelper.getAllNamesSNamesUsersEmailsPass();
                                    for (int i = 0; i < lst.get(0).size(); i++) {
                                        String name = lst.get(0).get(i);
                                        String sname = lst.get(1).get(i);
                                        String user = lst.get(2).get(i);
                                        if (!user.equals(userID)) {
                                %>

                                <option value="<%=user%>" ><%=name%> <%=sname%></option>
                                <%
                                        }
                                    }
                                %></select>
                        </form>
                    </td>

                    <td withd="150px">
                        <form method="post" name="ParticipantiOdzemiForm" action="ParticipantiOdzemi.do" onsubmit="return validateParticipantiOdzemiForm()">
                            <select size="8" name="ParticipantiOdzemi" multiple="multiple">
                                <%
                                    List<String> lst2 = DataBaseHelper.getPreferencesParticipant(userID);
                                    for (int i = 0; i < lst2.size(); i++) {
                                        String s = lst2.get(i);
                                %>
                                <option value="<%=s%>"><%=DataBaseHelper.getUserIme(s)%> <%=DataBaseHelper.getUserPrezime(s)%></option>
                                <%
                                    }
                                %>
                            </select>
                        </form>
                    </td> 
                </tr>
                <tr>
                    <td><input type="button" onclick="submitDodadi()" value="Додади"/></td>
                    <td><input type="button" onclick="submitOdzemi()" value="Одземи"/></td>
                </tr>
            </table>

            <p><a class="nazad" href="MainPage.jsp"><b>Назад</b></a></p>
        </div>
    </body>
</html>

<%-- 
    Document   : CreateModify
    Created on : Jan 7, 2012, 2:19:56 PM
    Author     : Home
--%>

<%@page import="java.util.List"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String userID = "";
        synchronized (session) {
            userID = (String) request.getSession().getAttribute("username");
        }
        String restorantCompare = DataBaseHelper.getPreferencesRestoran(userID);
        List<String> lst1 = DataBaseHelper.getPreferencesParticipant(userID);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Креирање на настан</title>
        <script type="text/javascript">
            
            function strcmp(a, b) {
                if (a.toString() < b.toString()) return -1;
                if (a.toString() > b.toString()) return 1;
                return 0;
            }
            
            function Mod(X, Y) { 
                return X - Math.floor(X/Y)*Y; 
            }
            
            function Div(X, Y) { 
                return Math.floor(X/Y);
            }
            
            function validateForma()
            {
                var x= document.forms["forma"]["ImeRestorant"].value;
                if(x==null || x=="")
                {
                    alert("Изберете ресторант");
                    return false;   
                }
                x = document.forms["forma"]["Vreme"].value;               
                if (x==null || x=="")
                {
                    alert("Изберете време");
                    return false;
                }
                var pat = /([01]\d|2[0-3]):[0-5]\d/;
                if(!pat.test(x))
                {
                    alert("Формат за време ЧЧ:ММ");
                    return false;
                }
                var now = new Date();
                var minutes = now.getMinutes();
                var hours = now.getHours();
                hours = hours + Div(minutes+30,60);
                minutes = Mod(minutes+45,60);
                if (hours<10)
                    hours = "0" + hours;
                if (minutes<10) 
                    minutes= "0" + minutes;
                var cmp = hours+":"+minutes;
                if( strcmp(cmp, x) > 0){
                    alert("Изберете време бар 45 минути по сегашниот момент");
                    return false;
                }    
            }
            
            function goToMain()
            {
                window.location="MainPage.jsp"
            }
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <form name="forma" method="post" action= "CreateNastan.do" onsubmit="return validateForma()">
                <table>
                    <tr>
                    <tr>
                        <th colspan="2">Локација и време на настанот</th><th>Покани</th>
                    </tr>
                    <td colspan="2" width="300px" >
                        <select name="ImeRestorant" size="1">
                            <option value="">-избери ресторант-</option>
                            <%
                                List<String> lst3 = DataBaseHelper.getAllRestaurantNames();
                                for (int i = 0; i < lst3.size(); i++) {
                                    String s = lst3.get(i);

                            %>
                            <option value="<%=s%>"
                                    <% if (s.equals(restorantCompare)) {
                                            out.write("selected=\"true\"");
                                        }%>
                                    ><%=s%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                    <td rowspan="3">
                        <select multiple="multiple" name="Pokani" size="8">
                            <%
                                List<String> lst = DataBaseHelper.getAllUsernames();
                                for (int i = 0; i < lst.size(); i++) {
                                    String s = lst.get(i);
                                    if (!s.equals(userID)) {
                            %>
                            <option value="<%=s%>"
                                    <%
                                        if (lst1.contains(s)) {
                                            out.write("selected=\"true\"");
                                        }
                                    %>
                                    ><%=DataBaseHelper.getUserIme(s)%> <%= DataBaseHelper.getUserPrezime(s)%></option>
                            <%
                                    }
                                }

                            %>
                        </select>
                    </td>
                    </tr>
                    <tr>
                        <td>Време:</td>
                        <td>
                            <input type="text" value="<%= DataBaseHelper.getPreferencesHour(userID)%>" name="Vreme"/>
                        </td>
                    </tr>
                    <tr allign="bottom" >
                        <td>
                            <input type="submit" value="Креирај"/>
                        </td>
                        <td>
                            <input type="button" onclick="goToMain()" value="Откажи"/>
                        </td>
                    </tr>

                </table>
            </form>
        </div>
    </body>
</html>

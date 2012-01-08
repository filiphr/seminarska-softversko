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
        String userID = (String) request.getSession().getAttribute("username");
        String restorantCompare = DataBaseHelper.getPreferencesRestoran(userID);
        List<String> lst1 = DataBaseHelper.getPreferencesParticipant(userID);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Креирање на настан</title>
        <script type="text/javascript">
            function validate()
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
            }
        </script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <form name="forma" method="post" action= "CreateNastan.do" onsubmit="return validate()">
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
                    <td colspan="2" >
                        <input type="submit" value="Креирај"/>
                    </td>
                </tr>

            </table>
        </form>
    </body>
</html>

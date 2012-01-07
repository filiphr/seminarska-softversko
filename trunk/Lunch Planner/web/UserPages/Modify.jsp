<%-- 
    Document   : Modify
    Created on : Jan 7, 2012, 5:56:10 PM
    Author     : Home
--%>

<%@page import="java.util.List"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String userID = (String) request.getSession().getAttribute("username");
        int groupID = DataBaseHelper.getGroupIDFromCreator(userID);
        String restorantCompare = DataBaseHelper.getRestaurantName(groupID);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Измена на настан</title>
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
        <%= userID %>
        <%= groupID %>
        <%= restorantCompare %>
        <jsp:include page="../header.jsp"/>
        <form name="forma" method="post" action= "ModifyNastan.do" onsubmit="return validate()">
            <table>
                <tr>
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
                </tr>
                <tr>
                    <td>Време:</td>
                    <td>
                        <input type="text" value="<%= DataBaseHelper.getVremeFromGroup(groupID)%>" name="Vreme"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Смени"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

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
        <title>JSP Page</title>
        <script type="text/javascript">
            function validatePreferenceForm()
            {
                
            }
        </script>
    </head>
    <body>
        <form action="" method="post">
            <table>
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
        <form name="PreferenceForm" method="post" action="Preference.do" onsubmit="return validatePreferenceForm()">
            <input type="hidden" name="ImeRestorant" value="<%=resNamePrefItem%>"/>
            <table>
                <tr><td colspan="2" width="300" px ><select  name="Meni" size="1">
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
                <tr><td>Време:</td><td><input type="text" name="Vreme"/>
            </table>
        </form>
    </body>
</html>

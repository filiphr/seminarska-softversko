<%-- 
    Document   : Invite
    Created on : 07-Jan-2012, 13:55:23
    Author     : Filip
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DataBase.DataBaseHelper"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <%
        Integer IDGroup = Integer.parseInt(request.getParameter("groupID"));
        String user = "";
        synchronized (session) {
            user = (String) session.getAttribute("username");
        }
    %>



    <body>
        <script>
            function formSubmit(source)
            {
                var myForm=document.getElementById("pokana");
                myForm.elements["source"].value=source;
                myForm.submit();
            }            
        </script>
        <h1>Hello World Invite!</h1>

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
                    <table border="2">
                        <tr>
                            <td>
                                <%
                                    List<String> lst1tmp = DataBaseHelper.getRestoranAndVreme(IDGroup);
                                    String Restorantmp = lst1tmp.get(0);
                                    String Vremetmp = lst1tmp.get(1);
                                %>
                                Ресторан:<%=Restorantmp%> Време:<%=Vremetmp%>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Гости кои се немаат приклучено на настан
                            </td>
                            <td>
                                Гости кои сакате вие да ги поканите
                            </td>
                        </tr>
                        <tr>
                            <td>          
                                <form method="POST" name="pokana" id="pokana" action="">
                                    <input type="hidden" name="source"/>
                                    <table>
                                        <tr>
                                            <td>                            
                                                <select  name="vraboteni" size="3" onchange="formSubmit('vraboteni')">
                                                    <option value="" disabled="true">Клик за да изберете вработен кој сакате да го поканите</option>
                                                    <%
                                                        List<List<String>> users = DataBaseHelper.getAllNamesSNamesUsersEmailsPass();
                                                        List<String> usersInGroup = DataBaseHelper.getNameSNameAndLunch(IDGroup).get(3);
                                                        List<String> pokaneti = (List<String>) session.getAttribute("pokaneti");
                                                        List<String> prefParticipanti = DataBaseHelper.getPreferencesParticipant(user);
                                                        String pokanet = request.getParameter("vraboteni");
                                                        String remove = request.getParameter("odbrani");

                                                        String pom = request.getParameter("source");
                                                        if (pokaneti == null) {
                                                            pokaneti = new ArrayList<String>();
                                                            for (int i = 0; i < prefParticipanti.size(); i++) {
                                                                pokaneti.add(prefParticipanti.get(i));
                                                            }
                                                        }
                                                        if ("vraboteni".equals(pom)) {
                                                            if (pokanet != null) {
                                                                pokaneti.add(pokanet);

                                                            }
                                                        } else if ("odbrani".equals(pom)) {
                                                            if (remove != null) {
                                                                pokaneti.remove(remove);
                                                            }
                                                        }


                                                        session.setAttribute("pokaneti", pokaneti);

                                                        for (int i = 0; i < users.get(0).size(); i++) {
                                                            String name = users.get(0).get(i);
                                                            String lastname = users.get(1).get(i);
                                                            String username = users.get(2).get(i);
                                                            if (!usersInGroup.contains(username)) {
                                                                if (!user.equals(username) && !pokaneti.contains(username) && !username.equals(pokanet)) {
                                                    %>

                                                    <option value="<%=username%>" ><%=name%> <%=lastname%></option>
                                                    <%
                                                                }
                                                            } else {
                                                                pokaneti.remove(username);
                                                            }
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                            <td>
                                                <select name="odbrani" size="3" onchange="formSubmit('odbrani')">
                                                    <option value="" disabled="true">Клик за да избришете покана</option>
                                                    <%


                                                        for (int i = 0; i < pokaneti.size(); i++) {
                                                            String tmpUsername = pokaneti.get(i);
                                                            String tmpName = DataBaseHelper.getUserIme(tmpUsername);
                                                            String tmpLastName = DataBaseHelper.getUserPrezime(tmpUsername);
                                                    %>
                                                    <option value="<%=tmpUsername%>"><%=tmpName%> <%=tmpLastName%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </form>

                                <form method="post" action="Pokani.do">
                                    <input type="hidden" name="groupID" value="<%=IDGroup%>"/>
                                    <table>
                                        <tr>
                                            <td>
                                                <input type="submit" value="Pokani"/>
                                            </td>
                                        </tr>
                                    </table>
                                </form>

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

    </body>
</html>

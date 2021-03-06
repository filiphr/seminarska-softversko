<%-- 
    Document   : Statistika
    Created on : Jan 7, 2012, 8:32:58 PM
    Author     : Home
--%>

<%@page import="model.SortedString"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="prediction.BuildC45forAllEmployee"%>
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
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Корисничка статистика</title>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <table width="100%">
                <%
                ArrayList<String> predikcija = new ArrayList<String>();  
                ArrayList<String> predikcijaUser = new ArrayList<String>();
                synchronized(application){  
                    BuildC45forAllEmployee bC45 = (BuildC45forAllEmployee) application.getAttribute("PredikcijaRestorani");
                      if (bC45!=null) {
                         List<String> restorani = DataBaseHelper.getAllRestaurantNames();
                         Calendar currentDate = Calendar.getInstance();
                        SimpleDateFormat formatter=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                          String dateNow = formatter.format(currentDate.getTime());
                          for(String restoran : restorani){
                              ArrayList<String> tmp = bC45.getPredictionRestoran(restoran, dateNow);
                              if (!tmp.isEmpty()){
                                  predikcija.addAll(tmp);
                              }
                          }
                         predikcijaUser.addAll(bC45.getPrediction(dateNow, userID));
                          //bC45.getPrediction(date, user)
                      }
                }
                predikcija = SortedString.getSorted(predikcija);
                
                %>
                <tr>
                    <td valign="top" align="center" width="50%">
                        <table class="predictStavka">
                            <% if (!predikcija.isEmpty()) { %>
                            <tr>
                                <%--<td>Веројатноста за одржување на креираните настани е следна:</td>--%>
                                <th>Ресторан</th>
                                <th>Креатор</th>
                                <th>Ќе се одржи</th>
                            </tr>
                            <%
                                for (String tmp : predikcija){
                                    if (!tmp.isEmpty()){
                                    String [] tmp2 = tmp.split(";");
                             %>
                             <tr>
                                 <td>
                                     <%=tmp2[0]%> 
                                 </td>
                                 <td>
                                     <%=tmp2[1]%>
                                 </td>
                                 <td>
                                     <%=tmp2[2]%>%
                                 </td>
                             </tr>
                                    <%}%>
                                <% }%>
                            <%} else {%>
                            <tr>
                                <td>
                                    Неможе да се направи предикција бидејќи нема креирани ресторан за овој ден
                                </td>
                            </tr>
                            <%}%>
                        </table>
                    </td>
                    <td valign="top" align="center" width="50%">
                        <table class="predictStavka">
                            <% if (!predikcijaUser.isEmpty()) { %>
                            <tr>
                                <%-- <td>Вие би оделе во следните ресторани:</td>--%>
                                <th>Ресторан</th>
                                <th>Би оделе</th>
                            </tr>
                            <%
                                for (String tmp : predikcijaUser){
                                    if (!tmp.isEmpty()){
                                    String [] tmp2 = tmp.split(";");
                             %>
                             <tr>
                                 <td>
                                     <%=tmp2[0]%> 
                                 </td>
                                 <td>
                                     <%=tmp2[1]%>
                                 </td>
                             </tr>
                                    <%}%>
                                <% }%>
                            <%} else {%>
                            <tr>
                                <td>
                                    Неможе да се направи предикција за Вас
                                </td>
                            </tr>
                            <%}%>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>

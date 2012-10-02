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

    <head>
        <script language="javaScript" type="text/javascript" src="calendar.js"></script>
        <link href="calendar.css" rel="stylesheet" type="text/css">
        <title>Ажурирање на податоци</title>
        <style type="text/css">
            a {
                font-size: medium;
                text-decoration: none;
                color: black;
            }
        </style>
        <script type="text/javascript">
            function check(date1, date2) {
                //                 var x = document.forms["AddMenuItemForm"]["ImeDodadi"].value
                //                if(x==null || x=="")
                //                {
                //                    alert("Изберете ресторант");
                //                    return false;
                //                }
                var x1 = document.forms["DateRestoran"]["datum1"].value;
                var x2 = document.forms["DateRestoran"]["datum2"].value;
                if((x1==null || x1=="") && (x2==null || x2=="")){
                    alert("Vnesete datum")
                    return false;
                }
                if(!(x1==null || x1=="")){
                    var s = date1.split('-');
                    var s1 = date2.split('-');
                    var s2 = x1.split('-');
                    var b = new Date();
                    b.setYear(s[0]);
                    b.setMonth(s[1], s[2]);
                    var e = new Date();
                    e.setYear(s1[0]);
                    e.setMonth(s1[1], s1[2]);
                    var c = new Date();
                    c.setYear(s2[0]);
                    c.setMonth(s2[1], s2[2]);
                    if(!(c <= e && c >= b)) {
                        alert("Datumot mora da bide pomegu " + date1 + " i " + date2);
                        return false;
                    }
		
                }
                if(!(x2==null || x2=="")){
                    var s = date1.split('-');
                    var s1 = date2.split('-');
                    var s2 = x2.split('-');
                    var b = new Date();
                    b.setYear(s[0]);
                    b.setMonth(s[1], s[2]);
                    var e = new Date();
                    e.setYear(s1[0]);
                    e.setMonth(s1[1], s1[2]);
                    var c = new Date();
                    c.setYear(s2[0]);
                    c.setMonth(s2[1], s2[2]);
                    if(!(c <= e && c >= b)) {
                        alert("Datumot mora da bide pomegu " + date1 + " i " + date2);
                        return false;
                    }
		
                }
                return true;
            }
        </script>
    </head>


    <body>
        <jsp:include page="../header.jsp"/>
        <div>
            <br/>
            <br/>
            <a href="AddRemoveUser.jsp">Ажурирање на листа на корисници</a>
            <br/>
            <br/>
            <a href="UpdateUser.jsp">Промена на податоци на корисник</a>
            <br/>
            <br/>
            <a href="AddRemoveRestorant.jsp">Ажурирање на листа на ресторанти</a>
            <br/>
            <br/>
            <a href="AddRemoveItem.jsp">Ажурирање на мени на ресторантите</a>
            <br/>
            <br/>
            <a href="Arhiviraj.do">Архивирање на групите</a>
            <br/>
            <br/>
            Datumot treba da bide pomegu <%=DataBaseHelper.getFirstDate()%> i <%=DataBaseHelper.getLastDate()%>
            <br/>
            <%-- TUKA TREBA DA SE BUTNE ARHIVIRANJE NA GRUPITE --%>
            <form name="DateRestoran" method="POST" action="DateForClassificator.do" onsubmit="return check('<%=DataBaseHelper.getFirstDate()%>', '<%=DataBaseHelper.getLastDate()%>')">
                Datum za restoran:<input type="text" name="datum1"><a href="#" onClick="setYears(2006, 2013);showCalender(this, 'datum1');"><img src="calender.png"></a>
                <br/>
                Datum za korisnik:<input type="text" name="datum2"><a href="#" onClick="setYears(2006, 2013);showCalender(this, 'datum2');"><img src="calender.png"></a>
                <br/>
                <input type="submit" value="Reobuci"/>
            </form>
            <table id="calenderTable">
                <tbody id="calenderTableHead">
                    <tr>
                        <td colspan="4" align="center">
                            <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
                                    this.selectedIndex, false));" id="selectMonth">
                                <option value="0">Jan</option>
                                <option value="1">Feb</option>
                                <option value="2">Mar</option>
                                <option value="3">Apr</option>
                                <option value="4">May</option>
                                <option value="5">Jun</option>
                                <option value="6">Jul</option>
                                <option value="7">Aug</option>
                                <option value="8">Sep</option>
                                <option value="9">Oct</option>
                                <option value="10">Nov</option>
                                <option value="11">Dec</option>
                            </select>
                        </td>
                        <td colspan="2" align="center">
                            <select onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
                            </select>
                        </td>
                        <td align="center">
                            <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
                        </td>
                    </tr>
                </tbody>
                <tbody id="calenderTableDays">
                    <tr style="">
                        <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
                    </tr>
                </tbody>
                <tbody id="calender"></tbody>
            </table>
        </div>
    </body>
</html>

<%@ page import="ScratchPad.WorkClasses.DBReader" %>
<%@ page import="ScratchPad.WorkClasses.Month" %>
<%@ page import="ScratchPad.WorkClasses.User" %>
<%-- TODO: CLEAN UP THE PAGE TAG ABOVE --%>

<%@ include file="calendarCommon.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ScratchPad: calendar</title>
    <link rel="StyleSheet" href="css/calendar.css" type="text/css" media="screen"/>
</head>

<body id="regular_page">
<div class="user">
    <p id="logo">ScratchPad</p>
    <button onclick="location.href='index.jsp'" style="float: right">Выйти</button>
    <%
        HttpSession httpSession = request.getSession(true);
        int id = (Integer) httpSession.getAttribute("id");
        User user = new DBReader().getUserById(id);
        out.print("<p>" + user.getName() + "</p>");
    %>

</div>
<div id="calendar_print_view_main_div">

    <div class="arnold">
        <table class="calendar_title">
            <tr>
                <td id="prev_link">
                    <button onclick="location.href='calendar?month=<%=prevMonth%>&year=<%=prevYear%>'"><b><<</b>
                    </button>
                    <br><br>
                </td>

                <td colspan="7" class="month_year_header">
                    <h2><a onclick="location.href=
                            'monthTask?month=<%=intMonth%>&monthName=<%=monthName%>&year=<%=intYear%>'" class = "month_text"><%=monthName%> </a>
                        <a onclick="location.href=
                                'yearTask?year=<%=intYear%>'" class = "year_text"><%=intYear%></a></h2>
                </td>

                <td id="next_link">
                    <button onclick="location.href='calendar?month=<%=nextMonth%>&year=<%=nextYear%>'"><b>>></b>
                    </button>
                    <br><br>
                </td>

            </tr>

        </table>

        <table class="calendar">

            <tr class="week_header_row">
                <th width="14%" class="th_day_cell day">Sun</th>
                <th width="14%" class="th_day_cell day">Mon</th>
                <th width="14%" class="th_day_cell day">Tue</th>
                <th width="14%" class="th_day_cell day">Wed</th>
                <th width="14%" class="th_day_cell day">Thu</th>
                <th width="15%" class="th_day_cell day">Fri</th>
                <th width="15%" class="th_day_cell day">Sat</th>
            </tr>
            <%
                {
                    try {
                        Month aMonth = Month.getMonth(Integer.parseInt(request.getParameter("month")), Integer.parseInt(request.getParameter("year")));
                    } catch (Exception e) {
                    }
                    Month aMonth = Month.getMonth(Integer.parseInt(currentMonthString), Integer.parseInt(currentYearString));
                    int[][] days = aMonth.getDays();
                    for (int i = 0; i < aMonth.getNumberOfWeeks(); i++) {%>
            <tr>
                <%
                    for (int j = 0; j < 7; j++) {
                        if (days[i][j] == 0) {%>
                <td class="empty_day_cell">&nbsp;</td>
                <%
                } else
                    if(days[i][j] == currentDayInt ){
                %>
                <td align="right" valign="top" class="current_day_cell"><a onclick="location.href=
                        'todo?day=<%=days[i][j]%>&month=<%=intMonth%>&monthName=<%=monthName%>&year=<%=intYear%>'"><%=days[i][j]%>
                </a></td>
                <%
                        } else {
                %> <td align="right" valign="top" class="day_cell"><a onclick="location.href=
                        'todo?day=<%=days[i][j]%>&month=<%=intMonth%>&monthName=<%=monthName%>&year=<%=intYear%>'"><%=days[i][j]%>
                </a></td>
                <%
                        }
                    } // end for %>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>
</div>
</body>
</html>



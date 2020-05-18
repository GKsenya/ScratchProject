<%@ page import="ScratchPad.WorkClasses.DBReader" %>
<%@ page import="ScratchPad.WorkClasses.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ScratchPad.WorkClasses.Task" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calendar</title>
    <link rel="stylesheet" href="css/remodel.css">
    <link rel="stylesheet" href="css/remodel-default-theme.css">
    <link rel="StyleSheet" href="css/calendar.css" type="text/css" media="screen"/>
    <link rel="StyleSheet" href="css/style.css" type="text/css" media="screen"/>
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,700&subset=latin,cyrillic' rel='stylesheet'
          type='text/css'>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/remodal.min.js"></script>
    <link type='text/css' href='css/style.css' rel='stylesheet'/>
</head>

<%
    HttpSession httpSession = request.getSession(true);
    int id = (Integer) httpSession.getAttribute("id");
    User user = new DBReader().getUserById(id);
    String month = (String) request.getAttribute("month");
    String monthName = (String) request.getAttribute("monthName");
    String year = (String) request.getAttribute("year");
    String day = (String) request.getAttribute("day");

    request.setAttribute("month", month);
    request.setAttribute("monthName", monthName);
    request.setAttribute("year", year);
    request.setAttribute("day", day);
    String date = day + "." + month + "." + year;
    String dateName = day + " " + monthName + " " + year;

    List<Task> fixedTask = (List<Task>) request.getAttribute("fixedTask");
    List<Task> unfixedTask = (List<Task>) request.getAttribute("unfixedTask");
%>

<body id="regular_page">
<div class="user">
    <button onclick="location.href='calendar.jsp?month=<%=month%>&year=<%=year%>'" style="float: left">Назад</button>
    <button onclick="location.href='index.jsp'" style="float: right">Выйти</button>
    <p><%=user.getName()%>
    </p>

</div>
<div id="calendar_print_view_main_div">
        <h2 class="month"><a class="year_text"><%=day%> </a><a onclick="location.href=
                'monthTask?month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>'" class = "month_text"><%=monthName%> </a>
            <a onclick="location.href=
                    'yearTask?year=<%=year%>'" class = "year_text"><%=year%></a></h2>
    <div class="arnold2">
        <table class="calendar_title">
            <%
                for (Task task : fixedTask) {
                    out.println("<tr><td class=\"time\">" + task.getTaskTime() + "</td>");
                    out.println("<td class=\"text\">" + task.getTask() + "</td>");
                    out.println("<td class=\"text\"><a href=\"#modal\" onclick=\"fixed("+task.getId() + ",'" + task.getTask()+ "','" + task.getTaskTime()+"')\" title=\"Редактировать\">\uD83D\uDD89</a></td>");
                    out.println("<td class=\"text\"><a href=\"delTask?day="+day+"&month="+month+"&monthName="+monthName+"&year="+year+"&taskId="+task.getId()+"\" title=\"Удалить\">&#10007;</a></td></tr>");
                }
            %>
            <tr>
                <td class="time">__:__</td>
                <td class="text">
                    <button type="button" style=" background-color: #6ad0e6"><a href="#modal" onclick="newfixed()" title="Добавить новую задачу">+</a></button>
                    <div class="remodal" data-remodal-id="modal" role="dialog" aria-labelledby="modal1Title"
                         aria-describedby="modal1Desc">
                        <div class="remodalBorder">
                            <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
                            <form class="task_form" id="form_fixed"
                                  action="newTask?day=<%=day%>&month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>"
                                  method="post" style="padding-bottom:1px">
                                <h2 class="signin-active" style="color: white" id="form_fixed_title">Добавление новой задачи</h2>
                                <label style="text-align: left; color: white">Время</label>
                                <input type="time" name="time" id="time_change_fixed" class="form-styling1" required>
                                <p class="smessage">${timeMes}</p>
                                <label style="text-align: left; color: white">Описание</label>
                                <input type="textarea" class="form-styling2" name="task" id="task_change_fixed"
                                       placeholder="Добавьте описание..." >
                                <p class="smessage">${taskMes}</p>
                                <input type="submit" class="btn-sign" value="Добавить" id="fixed_button">
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <div class="arnold3">
        <table class="calendar_title">

            <tr>
                <%
                    for (Task task : unfixedTask) {
                        out.println("<tr><td class=\"time\">" + "*" + "<td>");
                        out.println("<td class=\"text\">" + task.getTask() + "<td>");
                        out.println("<td class=\"text\"><a href=\"#modal12\" onclick=\"unfixed("+task.getId() + ",'" + task.getTask()+"')\" title=\"Редактировать\">\uD83D\uDD89</a></td>");
                        out.println("<td class=\"text\"><a href=\"delTask?day="+day+"&month="+month+"&monthName="+monthName+"&year="+year+"&taskId="+task.getId()+"\" title=\"Удалить\">&#10007;</a></td></tr>");
                    }
                %>
                <td class="time">*</td>

                <td class="text">
                    <button type="button" style=" background-color: #6ad0e6">
                        <a href="#modal12" onclick="newunfixed()" title="Добавить новую задачу">+</a>
                    </button>
                    <div class="remodal" data-remodal-id="modal12" role="dialog" aria-labelledby="modal1Title"
                         aria-describedby="modal1Desc">
                        <div class="remodalBorder">
                            <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
                            <form class="task_form"
                                  action="newTask?day=<%=day%>&month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>"
                                  method="post" id="form_unfixed">
                                <h2 class="new" style="color: white" id="form_unfixed_title">Добавление новой задачи</h2>
                                <label style="text-align: left; color: white">Описание</label>
                                <input type="textarea" class="form-styling2" name="task" id="task_change_unfixed"
                                       placeholder="Добавьте описание..." >
                                <input type="submit" name="submit" class="btn-sign" value="Добавить" id="unfixed_button">
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
<script>
    function fixed(id, task, time) {
        let task1 = document.getElementById("task_change_fixed").value = task;
        let time1 = document.getElementById("time_change_fixed").value = time;
        let form = document.getElementById("form_fixed").action = "editTask?day=<%=day%>&month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>&taskId=" + id;
        let formTitle = document.getElementById("form_fixed_title").textContent = "Редактирование задачи";
        let button = document.getElementById("fixed_button").value = "Изменить";
    }
    function unfixed(id, task) {
        let task1 = document.getElementById("task_change_unfixed").value = task;
        let form = document.getElementById("form_unfixed").action = "editTask?day=<%=day%>&month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>&taskId=" + id;
        let formTitle = document.getElementById("form_unfixed_title").textContent = "Редактирование задачи";
        let button = document.getElementById("unfixed_button").value = "Изменить";
    }
    function newfixed(){
        let task1 = document.getElementById("task_change_fixed").value = null;
        let time1 = document.getElementById("time_change_fixed").value = null;
        let form = document.getElementById("form_fixed").action = "newTask?day=<%=day%>&month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>";
        let formTitle = document.getElementById("form_fixed_title").textContent = "Добавление задачи";
        let button = document.getElementById("fixed_button").value = "Добавить";
    }
    function newunfixed() {
        let task1 = document.getElementById("task_change_unfixed").value = null;
        let form = document.getElementById("form_unfixed").action = "newTask?day=<%=day%>&month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>";
        let formTitle = document.getElementById("form_unfixed_title").textContent = "Добавление задачи";
        let button = document.getElementById("unfixed_button").value = "Добавить";
    }
</script>
</html>



<%@ page import="ScratchPad.WorkClasses.DBReader" %>
<%@ page import="ScratchPad.WorkClasses.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ScratchPad.WorkClasses.Task" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ScratchPad: month</title>
    <link rel="stylesheet" href="css/remodel.css">
    <link rel="stylesheet" href="css/remodel-default-theme.css">
    <link rel="StyleSheet" href="css/calendar.css" type="text/css" media="screen"/>
    <link rel="StyleSheet" href="css/style.css" type="text/css" media="screen"/>
    <link href='http://fonts.googleapis.com/css?family=Arizonia' rel='stylesheet' type='text/css'>
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

    request.setAttribute("month", month);
    request.setAttribute("monthName", monthName);
    request.setAttribute("year", year);

    List<Task> monthTask = (List<Task>) request.getAttribute("monthTask");
%>

<body id="regular_page">
<div class="user">
    <p id="logo">ScratchPad</p>
    <button onclick="location.href='calendar.jsp?month=<%=month%>&year=<%=year%>'" style="float: left">Назад</button>
    <button onclick="location.href='index.jsp'" style="float: right">Выйти</button>
    <p><%=user.getName()%></p>

</div>
<div id="calendar_print_view_main_div">
    <button onclick="location.href='calendar.jsp'" class="back_button"><p>‹</p></button>
        <h2 class="month"><a onclick="location.href=
                'monthTask?month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>'" class = "month_text"><%=monthName%> </a>
            <a onclick="location.href=
                    'yearTask?year=<%=year%>'" class = "year_text"><%=year%></a>
        </h2>
</div>
    <div class="arnold_month">
        <table class="calendar_title">

            <%
                for (Task task : monthTask) {
                    out.println("<tr><td class=\"time\">" + "*" + "</td>");
                    out.println("<td class=\"text\">" + task.getTask() + "</td>");
                    out.println("<td class=\"text\"><a href=\"#modal12\" onclick=\"change_task(" + task.getId() + ",'" + task.getTask() + "')\" title=\"Редактировать\">\uD83D\uDD89</a></td>");
                    out.println("<td class=\"text\"><a href=\"delTask?month=" + month + "&monthName=" + monthName + "&year=" + year + "&taskId=" + task.getId() + "\" title=\"Удалить\">&#10007;</a></td></tr>");
                }
            %>
            <tr>
                <td class="time">*</td>

                <td class="text">
                    <button type="button" style=" background-color: #6ad0e6"><a href="#modal12"
                                                                                    onclick="new_task()">+</a></button>
                    <div class="remodal" data-remodal-id="modal12" role="dialog" aria-labelledby="modal1Title"
                         aria-describedby="modal1Desc">
                        <div class="remodalBorder">
                            <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
                            <form class="task_form"
                                  action="newMonthTask?month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>"
                                  method="post"
                                  id="form">
                                <h2 class="new" style="color: white" id="form_title">Добавление новой задачи</h2>
                                <label style="text-align: left; color: white">Описание</label>
                                <input class="form-styling2" id="task_change" id="task_change" name="task"
                                       placeholder="Добавьте описание..." type="textarea">
                                <input type="submit" name="submit" class="btn-sign" value="Добавить" id="button">
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</body>
<script>
    function change_task(id, task) {
        let task1 = document.getElementById("task_change").value = task;
        let form = document.getElementById("form").action = "editTask?month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>&taskId=" + id;
        let formTitle = document.getElementById("form_title").textContent = "Редактирование задачи";
        let button = document.getElementById("button").value = "Изменить";
    }

    function new_task() {
        let task1 = document.getElementById("task_change").value = null;
        let form = document.getElementById("form").action = "newMonthTask?month=<%=month%>&monthName=<%=monthName%>&year=<%=year%>";
        let formTitle = document.getElementById("form_title").textContent = "Добавление задачи";
        let button = document.getElementById("button").value = "Добавить";
    }
</script>
</html>

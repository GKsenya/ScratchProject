package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceWriter;
import ScratchPad.WorkClasses.DBWriter;
import ScratchPad.WorkClasses.InfoValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        InfoValidation infoValidation = new InfoValidation(req);
        int exceptionCounter = infoValidation.isValidTask();
        String month = req.getParameter("month");
        String monthName = req.getParameter("monthName");
        String year = req.getParameter("year");
        String day = req.getParameter("day");
        String date = month+"."+year;

        int taskId = Integer.parseInt(req.getParameter("taskId"));

        ResourceWriter dbWriter = new DBWriter();
        String nextJSP = new String();
        if (day == null) {
            if (month == null) {
                dbWriter.editDayTask(taskId, infoValidation.getTask(), null, "task_for_year", date);
                nextJSP = "yearTask?year=" + year;
            } else {
                dbWriter.editDayTask(taskId, infoValidation.getTask(), null, "task_for_month", date);
                nextJSP = "monthTask?month=" + month + "&monthName=" + monthName + "&year=" + year;
            }
        } else {
            dbWriter.editDayTask(taskId, infoValidation.getTask(), infoValidation.getTime(), "task_for_day", null);
            nextJSP = "todo?day=" + day + "&month=" + month + "&monthName=" + monthName + "&year=" + year;
        }

        resp.sendRedirect(nextJSP);
    }
}

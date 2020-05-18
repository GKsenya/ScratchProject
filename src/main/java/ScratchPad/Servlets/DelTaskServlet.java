package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceWriter;
import ScratchPad.WorkClasses.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String month = req.getParameter("month");
        String monthName = req.getParameter("monthName");
        String year = req.getParameter("year");
        String day = req.getParameter("day");

        int taskId = Integer.parseInt(req.getParameter("taskId"));
        ResourceWriter dbWriter = new DBWriter();
        String nextJSP = new String();
        if(day == null){
            if(month == null){
                dbWriter.deleteDayTask(taskId, "task_for_year");
                nextJSP = "yearTask?year=" + year;
            } else{
                dbWriter.deleteDayTask(taskId, "task_for_month");
                nextJSP = "monthTask?month=" + month + "&monthName=" + monthName + "&year=" + year;
            }
        }else {
            dbWriter.deleteDayTask(taskId, "task_for_day");
            nextJSP = "todo?day=" + day + "&month=" + month + "&monthName=" + monthName + "&year=" + year;
        }

        resp.sendRedirect(nextJSP);

    }
}

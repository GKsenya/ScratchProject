package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceWriter;
import ScratchPad.WorkClasses.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        InfoValidation infoValidation = new InfoValidation(req);
        int exceptionCounter = infoValidation.isValidTask();

        String month = req.getParameter("month");
        String monthName = req.getParameter("monthName");
        String year = req.getParameter("year");
        String day = req.getParameter("day");

        req.setAttribute("day", req.getParameter("day"));
        req.setAttribute("month", req.getParameter("month"));
        req.setAttribute("year", req.getParameter("year"));
        req.setAttribute("monthName", req.getParameter("monthName"));
        String date = day+"."+month+"."+year;

        int id = (Integer) req.getSession().getAttribute("id");

        if(exceptionCounter==0){
            ResourceWriter dbWriter = new DBWriter();
            dbWriter.saveNewTask(id, new Task(infoValidation.getTask(), date, infoValidation.getTime()));
            String nextJSP = "todo?day="+day+"&month="+month+"&monthName="+monthName+"&year="+year;
            resp.sendRedirect( nextJSP);
        }
        else {
            String nextJSP = "todo?day="+day+"&month="+month+"&monthName="+monthName+"&year="+year;;
            resp.sendRedirect( nextJSP);
        }

    }
}

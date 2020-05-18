package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceWriter;
import ScratchPad.WorkClasses.DBWriter;
import ScratchPad.WorkClasses.InfoValidation;
import ScratchPad.WorkClasses.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewMonthTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        InfoValidation infoValidation = new InfoValidation(req);
        int exceptionCounter = infoValidation.isValidTask();

        String month = req.getParameter("month");
        String monthName = req.getParameter("monthName");
        String year = req.getParameter("year");

        req.setAttribute("month", req.getParameter("month"));
        req.setAttribute("year", req.getParameter("year"));
        req.setAttribute("monthName", req.getParameter("monthName"));
        String date = month+"."+year;

        int id = (Integer) req.getSession().getAttribute("id");

        if(exceptionCounter==0){
            ResourceWriter dbWriter = new DBWriter();
            dbWriter.saveNewMonthYearTask(id, new Task(infoValidation.getTask(), date));
            String nextJSP = "monthTask?month="+month+"&monthName=" + monthName + "&year="+year;
            resp.sendRedirect( nextJSP);
        }
        else {
            String nextJSP = "monthTask?month="+month+"&monthName=" + monthName + "&year="+year;
            resp.sendRedirect( nextJSP);
        }

    }
}

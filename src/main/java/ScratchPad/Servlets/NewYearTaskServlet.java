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

public class NewYearTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        InfoValidation infoValidation = new InfoValidation(req);
        int exceptionCounter = infoValidation.isValidTask();

        String year = req.getParameter("year");
        req.setAttribute("year", req.getParameter("year"));
        int id = (Integer) req.getSession().getAttribute("id");

        if(exceptionCounter==0){
            ResourceWriter dbWriter = new DBWriter();
            Task task = new Task(infoValidation.getTask(), year);
            dbWriter.saveNewMonthYearTask(id, task);
            String nextJSP = "yearTask?year="+year;
            resp.sendRedirect( nextJSP);
        }
        else {
            String nextJSP = "yearTask?year="+year;
            resp.sendRedirect( nextJSP);
        }

    }
}

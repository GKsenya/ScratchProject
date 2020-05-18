package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceReader;
import ScratchPad.WorkClasses.DBReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MonthTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = (Integer) request.getSession().getAttribute("id");
        String month = request.getParameter("month");
        request.setAttribute("month", month);
        String year = request.getParameter("year");
        request.setAttribute("year", year);
        request.setAttribute("monthName", request.getParameter("monthName"));
        String date = month+"."+year;

        ResourceReader dbReader = new DBReader();
        request.setAttribute("monthTask", dbReader.getMonthTasks(id, date));
        RequestDispatcher dispatcher = request.getRequestDispatcher("month.jsp");
        dispatcher.forward(request, response);
    }
}

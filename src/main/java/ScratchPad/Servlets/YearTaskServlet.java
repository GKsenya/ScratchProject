package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceReader;
import ScratchPad.WorkClasses.DBReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YearTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = (Integer) request.getSession().getAttribute("id");
        String year = request.getParameter("year");
        request.setAttribute("year", year);
        String date = year;

        ResourceReader dbReader = new DBReader();
        request.setAttribute("yearTask", dbReader.getMonthTasks(id, date));
        RequestDispatcher dispatcher = request.getRequestDispatcher("year.jsp");
        dispatcher.forward(request, response);
    }
}

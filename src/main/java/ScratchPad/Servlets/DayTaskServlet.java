package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceReader;
import ScratchPad.WorkClasses.DBReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DayTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = (Integer) request.getSession().getAttribute("id");
        String day = request.getParameter("day");
        request.setAttribute("day", day);
        String month = request.getParameter("month");
        request.setAttribute("month", month);
        String year = request.getParameter("year");
        request.setAttribute("year", year);
        request.setAttribute("monthName", request.getParameter("monthName"));
        String date = day+"."+month+"."+year;

        ResourceReader dbReader = new DBReader();
        dbReader.getTasks(id, date);
        request.setAttribute("fixedTask", dbReader.fixedTasks());
        request.setAttribute("unfixedTask", dbReader.unfixedTasks());
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo.jsp");
        dispatcher.forward(request, response);
        }
}

package ScratchPad.Servlets;

import ScratchPad.Interfaces.ResourceReader;
import ScratchPad.WorkClasses.DBReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CalendarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("id");
        ResourceReader rur = new DBReader();
        req.setAttribute("user", rur.getUserById(id));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("calendar.jsp");
        requestDispatcher.forward(req, resp);
    }
}

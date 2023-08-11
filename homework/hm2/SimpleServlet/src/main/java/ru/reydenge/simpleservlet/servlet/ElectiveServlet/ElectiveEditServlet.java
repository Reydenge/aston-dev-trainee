package ru.reydenge.simpleservlet.servlet.ElectiveServlet;

import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.service.ElectiveServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ElectiveEditServlet")
public class ElectiveEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Update Elective</h1>");
            int id = Integer.parseInt(request.getParameter("id"));
            Elective elective = ElectiveServiceImpl.getElectiveService().getElectiveById(id);
            out.print("<form action='ElectiveEditServlet' method='post'>");
            out.print("<table>");
            out.print("<tr><td></td><td><input type='hidden' name='id' value='" + elective.getId() +
                    "'/></td></tr>");
            out.print("<tr><td>Name:</td><td><input type='text' name='title' value='" + elective.getTitle() +
                    "'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
            out.print("</table>");
            out.print("</form>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");

        Elective elective = new Elective();
        elective.setId(id);
        elective.setTitle(title);

        int status = ElectiveServiceImpl.getElectiveService().updateElective(elective);
        if (status > 0) {
            response.sendRedirect("ElectiveViewServlet");
        } else {
            out.println("Sorry! Unable to update record");
        }
        out.close();
    }
}

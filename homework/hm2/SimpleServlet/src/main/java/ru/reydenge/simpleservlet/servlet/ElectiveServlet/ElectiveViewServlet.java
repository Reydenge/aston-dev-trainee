package ru.reydenge.simpleservlet.servlet.ElectiveServlet;

import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.service.ElectiveServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ElectiveViewServlet")
public class ElectiveViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='electiveIndex.html'>Add New Elective</a>");
        out.println("<h1>Elective List</h1>");

        List<Elective> electiveList = ElectiveServiceImpl.getElectiveService().getAllElectives();

        out.print("<table border='1' bordercolor='#009879' width='50%'");
        out.print("<tr><th>Id</th><th>Title</th><th>Edit</th><th>Delete</th></tr>");

        for (Elective elective : electiveList) {
            out.print("<tr><td >" + elective.getId() +
                    "</td><td>" + elective.getTitle() +
                    "</td><td><a href='ElectiveEditServlet?id=" + elective.getId() +
                    "'>edit</a></td><td><a href='ElectiveDeleteServlet?id=" + elective.getId() + "'>delete</a></td></tr>");
        }
        out.print("</table>");
        out.print("<br>");
        out.print("<a href='/EnrollServlet'>Enroll to elective</a>");
        out.close();
    }
}

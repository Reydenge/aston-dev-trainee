package ru.reydenge.simpleservlet.servlet.StudentServlet;

import ru.reydenge.simpleservlet.service.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentServiceImpl.getStudentService().deleteStudentById(id);
        response.sendRedirect("StudentViewServlet");
    }
}

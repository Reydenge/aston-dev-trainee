package ru.reydenge.simpleservlet.servlet.StudentServlet;

import ru.reydenge.simpleservlet.entity.Student;
import ru.reydenge.simpleservlet.service.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/StudentEditServlet")
public class StudentEditServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Update Student</h1>");
            int id = Integer.parseInt(request.getParameter("id"));
            Student student = StudentServiceImpl.getStudentService().getStudentById(id);
            out.print("<form action='StudentEditServlet' method='post'>");
            out.print("<table>");
            out.print("<tr><td></td><td><input type='hidden' name='id' value='" + student.getId() +
                    "'/></td></tr>");
            out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + student.getName() +
                    "'/></td></tr>");
            out.print("<tr><td>University Name:</td><td><input type='text' name='universityName' value='" +
                    student.getUniversityName() + "'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
            out.print("</table>");
            out.print("</form>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String universityName = request.getParameter("universityName");

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setUniversityName(universityName);

        int status = StudentServiceImpl.getStudentService().updateStudent(student);
        if (status > 0) {
            response.sendRedirect("StudentViewServlet");
        } else {
            out.println("Sorry! Unable to update record");
        }
        out.close();
    }
}

package ru.reydenge.simpleservlet.servlet.StudentServlet;

import ru.reydenge.simpleservlet.entity.Student;
import ru.reydenge.simpleservlet.service.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/StudentViewServlet")
public class StudentViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<a href='studentIndex.html'>Add New Student</a>");
        out.println("<h1>Students List</h1>");

        List<Student> studentList = StudentServiceImpl.getStudentService().getAllStudents();

        out.print("<table border='1' bordercolor='#009879' width='50%'");
        out.print("<tr><th>Id</th><th>Name</th><th>University</th><th>Edit</th><th>Delete</th></tr>");

        for (Student student : studentList) {
            out.print("<tr><td >" + student.getId() +
                    "</td><td>" + student.getName() +
                    "</td><td>" + student.getUniversityName() +
                    "</td><td><a href='StudentEditServlet?id=" + student.getId() +
                    "'>edit</a></td><td><a href='StudentDeleteServlet?id=" + student.getId() + "'>delete</a></td></tr>");
        }
        out.print("</table>");

        out.print("<a href='/EnrollServlet'>Electives Enrollment</a>");

        out.close();
    }
}

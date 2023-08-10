package ru.reydenge.simpleservlet.servlet.StudentServlet;

import ru.reydenge.simpleservlet.entity.Student;
import ru.reydenge.simpleservlet.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/StudentSaveServlet")
public class StudentSaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String universityName = request.getParameter("universityName");
            if (name == null || universityName == null) {
                out.println("Invalid request parameters");
                return;
            }
            Student newStudent = new Student(name, universityName);
            int status;
            try {
                status = StudentServiceImpl.getStudentService().createStudent(newStudent);
            } catch (Exception e) {
                out.println("Error creating new student");
                return;
            }
            if (status > 0) {
                out.println("Record saved successfully");
                request.getRequestDispatcher("studentIndex.html").include(request, response);
            } else {
                out.println("Unable to save the record");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

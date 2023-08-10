package ru.reydenge.simpleservlet.servlet.StudentServlet;

import ru.reydenge.simpleservlet.repository.ElectiveRepository;
import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.entity.Student;
import ru.reydenge.simpleservlet.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EnrollServlet")
public class StudentEnrollToElectiveServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = StudentServiceImpl.getStudentService().getAllStudents();
        List<Elective> electives = ElectiveRepository.getAllElectives();

        request.setAttribute("students", students);
        request.setAttribute("electives", electives);
        request.getRequestDispatcher("/enrollIndex.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int electiveId = Integer.parseInt(request.getParameter("electiveId"));

        StudentServiceImpl.getStudentService().enrollStudentToElective(studentId, electiveId);

        response.sendRedirect(request.getContextPath() + "/StudentViewServlet");
    }
}

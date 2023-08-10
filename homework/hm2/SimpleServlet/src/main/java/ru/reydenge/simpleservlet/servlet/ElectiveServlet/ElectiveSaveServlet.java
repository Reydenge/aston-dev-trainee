package ru.reydenge.simpleservlet.servlet.ElectiveServlet;

import ru.reydenge.simpleservlet.repository.ElectiveRepository;
import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.service.ElectiveServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ElectiveSaveServlet")
public class ElectiveSaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            String title = request.getParameter("title");
            if (title == null) {
                out.println("Invalid request parameters");
                return;
            }
            Elective newElective = new Elective(title);
            int status;
            try {
                status = ElectiveServiceImpl.getElectiveService().createElective(newElective);
            } catch (Exception e) {
                out.println("Error creating new elective");
                return;
            }
            if (status > 0) {
                out.println("Record saved successfully");
                request.getRequestDispatcher("electiveIndex.html").include(request, response);
            } else {
                out.println("Unable to save the record");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

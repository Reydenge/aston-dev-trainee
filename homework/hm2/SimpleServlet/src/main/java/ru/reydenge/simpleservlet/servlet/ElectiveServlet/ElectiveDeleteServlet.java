package ru.reydenge.simpleservlet.servlet.ElectiveServlet;

import ru.reydenge.simpleservlet.service.ElectiveServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ElectiveDeleteServlet")
public class ElectiveDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ElectiveServiceImpl.getElectiveService().deleteElectiveById(id);
        response.sendRedirect("ElectiveViewServlet");
    }
}

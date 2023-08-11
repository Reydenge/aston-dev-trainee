<%@ page import="ru.reydenge.simpleservlet.entity.Elective" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.reydenge.simpleservlet.entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Elective Enrollment</title>
</head>
<body>
<h1>Student Elective Enrollment</h1>
<form action="EnrollServlet" method="post">
    <label for="student">Select a student:</label>
    <select name="studentId" id="student">
        <% for (Student student : (List<Student>) request.getAttribute("students")) { %>
        <option value="<%= student.getId() %>"><%= student.getName() %></option>
        <% } %>
    </select>
    <br>
    <label for="elective">Select a elective:</label>
    <select name="electiveId" id="elective">
        <% for (Elective elective : (List<Elective>) request.getAttribute("electives")) { %>
        <option value="<%= elective.getId() %>"><%= elective.getTitle() %></option>
        <% } %>
    </select>
    <br>
    <input type="submit" value="Enroll">
</form>
</body>
</html>
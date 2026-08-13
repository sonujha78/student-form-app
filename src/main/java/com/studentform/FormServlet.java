package com.studentform;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/submit")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // ---- 1. Read + basic validation ----
        String firstName = trim(request.getParameter("firstName"));
        String lastName = trim(request.getParameter("lastName"));
        String dob = trim(request.getParameter("dob"));
        String gender = trim(request.getParameter("gender"));
        String qualification = trim(request.getParameter("highestqualification"));
        String yearStr = trim(request.getParameter("year_of_passing"));
        String mobile = trim(request.getParameter("mobilenumber"));

        StringBuilder errors = new StringBuilder();

        if (isEmpty(firstName)) errors.append("First Name is required.<br>");
        if (isEmpty(lastName)) errors.append("Last Name is required.<br>");
        if (isEmpty(dob)) errors.append("Date of Birth is required.<br>");
        if (isEmpty(gender)) errors.append("Gender is required.<br>");
        if (isEmpty(qualification)) errors.append("Highest Qualification is required.<br>");
        if (isEmpty(yearStr)) errors.append("Year of Passing is required.<br>");
        if (isEmpty(mobile)) errors.append("Mobile Number is required.<br>");

        if (!isEmpty(mobile) && !mobile.matches("^[0-9]{10}$")) {
            errors.append("Mobile Number must be exactly 10 digits.<br>");
        }

        int year = 0;
        if (!isEmpty(yearStr)) {
            try {
                year = Integer.parseInt(yearStr);
                if (year < 1950 || year > 2100) {
                    errors.append("Year of Passing looks invalid.<br>");
                }
            } catch (NumberFormatException e) {
                errors.append("Year of Passing must be a number.<br>");
            }
        }

        if (errors.length() > 0) {
            showResult(response, false, "Validation failed:<br>" + errors);
            return;
        }

        // ---- 2. Save to PostgreSQL ----
        String sql = "INSERT INTO \"formDetails\" " +
                "(\"firstName\", \"lastName\", dob, gender, highestqualification, year_of_passing, mobilenumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setDate(3, java.sql.Date.valueOf(dob));
            ps.setString(4, gender);
            ps.setString(5, qualification);
            ps.setInt(6, year);
            ps.setString(7, mobile);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                showResult(response, true, "Form submitted successfully!");
            } else {
                showResult(response, false, "Submission failed. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showResult(response, false, "Database error: " + escapeHtml(e.getMessage()));
        }
    }

    private void showResult(HttpServletResponse response, boolean success, String message)
            throws IOException {
        response.getWriter().println("<html><head><title>Submission Result</title>"
                + "<style>body{font-family:Arial;text-align:center;margin-top:80px;}"
                + ".ok{color:green;} .fail{color:red;}"
                + "a{display:inline-block;margin-top:20px;}</style></head><body>"
                + "<h2 class='" + (success ? "ok" : "fail") + "'>" + message + "</h2>"
                + "<a href='index.jsp'>&larr; Back to Form</a>"
                + "</body></html>");
    }

    private String trim(String s) {
        return s == null ? "" : s.trim();
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}

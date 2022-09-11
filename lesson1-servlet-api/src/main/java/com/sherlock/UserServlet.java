package com.sherlock;

import com.sherlock.persist.User;
import com.sherlock.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = new UserRepository();
        this.userRepository.insert(new User("Vladimir"));
        this.userRepository.insert(new User("Anastasia"));
        this.userRepository.insert(new User("Evgeniy"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String contextPath = request.getContextPath();

        response.getWriter().println("<p>request.getPathInfo(): " + pathInfo + "</p>");
        response.getWriter().println("<p>request.getContextPath(): " + contextPath + "</p>");

        PrintWriter writer = response.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>Id</th>");
        writer.println("<th>Username</th>");
        writer.println("</tr>");

        for (User user : userRepository.findAll()) {
            writer.println("<tr>");
            //writer.println("<td>" + user.getId() + "</td>");
            writer.println("<td><a href='" + getServletContext().getContextPath() + "/user/" + user.getId() + "'>" + user.getId() + "</a></td>");
            writer.println("<td>" + user.getUsername() + "</td>");
            writer.println("</tr>");
        }
    }
}

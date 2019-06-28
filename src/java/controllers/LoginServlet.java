/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anastasios
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //String username = request.getParameter("username");
            //String password = request.getParameter("password");
            //if(username.equals("tasos")&&password.equals("1234")){
            boolean passed = (boolean) (request.getAttribute("passed"));
            if (passed) {
                //redirect sends back to the client the path and the client makes a new request to this path.
                //The client first send a POST request, but with redirect, a new GET request will be sent.
                //https://blog.frankel.ch/refining-redirect-semantics-servlet-api/
                response.sendRedirect(request.getContextPath() + "/home.html");
            } else {
                int numOfTries = (int) request.getAttribute("tries");
                //out.println("Please enter the correct credentials");
                out.println("<p>Come on Maria. You can do it.</p>"
                        + "<p>You want a <b>hint</b>??? Do you like <b>pilates</b>?</p>");
                //out.print("<p>You have " + numOfTries + " failed logins</p>");
                //RequestDispatcher forwards/includes the request and response to other Servlet/HTML page/JSP.
                //Then the new path is sent back to the client.
                //https://www.javatpoint.com/requestdispatcher-in-servlet
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
                dispatcher.include(request, response);

            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

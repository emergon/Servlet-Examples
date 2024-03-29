/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CustomerService;

/**
 *
 * @author tasos
 */
public class CustomerServlet extends HttpServlet {

    private long time;
    CustomerService service;

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
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(getService().getStringCustomers());
            //out.print(getSessionDetails(request));
        }
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
        doGet(request, response);
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

    public CustomerService getService() {
        if (service == null) {
            service = new CustomerService();
        }
        return service;
    }

    public String getSessionDetails(HttpServletRequest req) {
        StringBuilder builder = new StringBuilder();
        //Get The Session from the user and count the inactive time
        HttpSession session = req.getSession();
        String sessionId = session.getId();
        if (time == 0) {
            time = session.getCreationTime();
        }
        long lastAccessedTime = session.getLastAccessedTime();
        long secondsInactive = (lastAccessedTime - time) / 1000;
        time = lastAccessedTime;
        int minutes = session.getMaxInactiveInterval() / 60;
        builder.append("<table>")
                .append("<tr>")
                .append("<td>").append("Session id").append("</td>").append("<td>").append(sessionId).append("</td>")
                .append("</tr>")
                .append("<tr>")
                .append("<td>").append("Session duration").append("</td>").append("<td>").append(minutes).append(" minutes").append("</td>")
                .append("</tr>")
                .append("<tr>")
                .append("<td>").append("Session inactive").append("</td>").append("<td>").append(secondsInactive).append(" seconds").append("</td>")
                .append("</tr>")
                .append("</table>");
        //Finished counting the inactive time for a user.
        return builder.toString();
    }
}

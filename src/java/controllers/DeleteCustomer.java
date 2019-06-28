/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.CustomerService;

/**
 *
 * @author tasos
 */
public class DeleteCustomer extends HttpServlet {

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
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String param = en.nextElement();
            String value = request.getParameter(param);
            if (param.equals("method") && value.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                boolean deleted = getService().deleteCustomerById(id);
                try (PrintWriter out = response.getWriter()) {
                    if (deleted) {
                        out.println("<h2>Customer deleted successfully</h2>");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Customers");
                        dispatcher.include(request, response);

                    } else {
                        out.println("<h2>Customer deletion failed</h2>");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("Customers");
                        dispatcher.include(request, response);
                    }
                }
            }
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Customer;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UpdateCustomer extends HttpServlet {

    private CustomerService service;

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
        String value = request.getParameter("method");
        int code = Integer.parseInt(request.getParameter("id"));
        if (value.equals("update")) {
            try (PrintWriter out = response.getWriter()) {
                out.print(getService().getCustomerUpdateFormById(code));
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
        Customer c = new Customer(Integer.parseInt(request.getParameter("ccode")), request.getParameter("cname"));
        boolean updated = getService().saveCustomer(c);
        if (updated) {
            try (PrintWriter out = response.getWriter()) {
                out.print("<p>Customer updated successfully</p>");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Customers");
                dispatcher.include(request, response);
            }
        }else{
            try(PrintWriter out = response.getWriter()){
                out.print("<p>Customer update failed</p>");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Customers");
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

    private CustomerService getService() {
        if (service == null) {
            service = new CustomerService();
        }
        return service;
    }
}

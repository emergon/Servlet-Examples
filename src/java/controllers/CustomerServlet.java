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
            out.println("<title>Servlet CustomerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String message="";
        Enumeration<String> en = request.getParameterNames();
        while(en.hasMoreElements()){
            String param = en.nextElement();
            String value = request.getParameter(param);
            if(param.equals("method")&&value.equals("delete")){
                boolean deleted = getService().deleteCustomerById(Integer.parseInt(request.getParameter("id")));
                if(deleted){
                    message = "<p>Customer deleted successfully</p>";
                }else{
                    message = "<p>Customer not deleted</p>";
                }
                
            }
        }
        
        try(PrintWriter out = response.getWriter()){
            //Get The Session from the user and count the inactive time
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            if(time==0){
                time = session.getCreationTime();
            }
            long lastAccessedTime = session.getLastAccessedTime();
            long secondsInactive = (lastAccessedTime - time)/1000;
            time = lastAccessedTime;
            int minutes = session.getMaxInactiveInterval()/60;
            out.print("<p>The session id is:<b>"+sessionId+"</b></p>");
            out.print("<p>The minutes to end the session are:<b>"+minutes+"</b></p>");
            out.print("<p>Session was inactive for <b>"+secondsInactive+"</b> seconds</p>");
            //Finished counting the inactive time for a user.
            if(message.length()>4){
                out.print(message);
            }
            out.println(getService().getStringCustomers());
        }
        
        //request.setAttribute("message", "tasos");
        
//        List<Customer> list = getService().getCustomers();
//        request.setAttribute("customers",list);
//        getServletContext().getRequestDispatcher("/WEB-INF/Customer2.jsp").forward(request, response);
        
//        RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/Customer.jsp");
//        view.forward(request,response);
        
        
        
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getService();
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getService();
        
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
        Customer c = new Customer();
        c.setCcode(Integer.parseInt(request.getParameter("ccode")));
        c.setCname(request.getParameter("cname"));
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Insert Customer</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServlet at " + request.getContextPath() + "</h1>");
            if(getService().insert(c)){
                out.println("<h2>Customer inserted successfully</h2>");
            }else{
                out.println("<h2>Customer insertion failed</h2>");
            }
            out.println("</body>");
            out.println("</html>");
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
    
    public CustomerService getService(){
        if(service == null){
            service = new CustomerService();
        }
        return service;
    }
}

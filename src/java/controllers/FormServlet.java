/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anastasios
 */
public class FormServlet extends HttpServlet {

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
            out.println("<title>Servlet FormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormServlet at " + request.getContextPath() + "</h1>");
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
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormServlet at " + request.getContextPath() + "</h1>");
            out.print("<table border=\"1px\" align=\"center\">");
            out.print("<thead>\n <tr>\n <td>Parameters</td>\n <td>Values</td>\n </tr>\n </thead>");
            out.print(getEnumeration(request));
            out.print("</table>");
            
            //out.print(getMap(request.getParameterMap()));
            
            //out.print(getFoods(request.getParameterValues("foods")));
            out.println("<p><a href='form.html'>Go Back</a></p>");
            out.println("</body>");
            out.println("</html>");

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
        processRequest(request, response);
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

    private String getFoods(String[] foods) {
        StringBuilder builder = new StringBuilder();
        builder.append("<h3>Foods Selected</h3>");
        builder.append("<ul>");
        for (String food : foods) {
            builder.append("<li>").append(food).append("</li>");
        }
        builder.append("</ul>");
        return builder.toString();
    }

    private String getMap(Map<String, String[]> map) {
        StringBuilder builder = new StringBuilder();
        builder.append("<h3>Map details</h3>");
        builder.append("<ul>");
        builder.append("<li>Size of map is ").append(map.size()).append("</li>");
        Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String[]> entry = it.next();
            builder.append("<li><b>Key</b>:")
                    .append(entry.getKey())
                    .append(" with values:");
            for (String s : entry.getValue()) {
                builder.append(s).append(",");
            }
            builder.append("</li>");
        }
        builder.append("</ul>");
        return builder.toString();
    }

    private String getEnumeration(HttpServletRequest req) {
        StringBuilder builder = new StringBuilder();
        Enumeration<String> parameters = req.getParameterNames();
        while(parameters.hasMoreElements()){
            String param = parameters.nextElement();
            builder.append("<tr>");
            builder.append("<td>").append(param).append("</td>");
            String [] values = req.getParameterValues(param);
            builder.append("<td>");
            if(values.length>1){
                builder.append("<ul>");
                for(String value:values){
                    builder.append("<li>").append(value).append("</li>");
                }
                builder.append("</ul>");
            }else{
                if(values[0].length()==0){
                    builder.append("Value is empty");
                }else{
                    builder.append(values[0]);
                }
                
            }
            builder.append("</td>");
            builder.append("</tr>");
        }
//        builder.append("<h3>Enumeration values</h3>");
//        builder.append("<ul>");
//        while (en.hasMoreElements()) {
//            String element = en.nextElement();
//            
//            builder.append("<li><b>element</b>:").append(element).append("</li>");
//        }
//        builder.append("</ul>");
        return builder.toString();
    }

    private String getParameterValue(String value) {
        StringBuilder builder = new StringBuilder();
        builder.append("<li>").append(value).append("</li>");
        return builder.toString();
    }
}

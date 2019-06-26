/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CustomerDao;
import entities.Customer;
import java.util.List;

/**
 *
 * @author tasos
 */
public class CustomerService {

    public List<Customer> getCustomers() {
        CustomerDao cdao = new CustomerDao();
        return cdao.getCustomers();
    }
    
    public boolean insert(Customer c){
        CustomerDao cdao = new CustomerDao();
        if(cdao.insertCustomer(c)){
            return true;
        }
        return false;
//        StringBuilder builder = new StringBuilder();
//        builder.append(getHeader("Insert Customer"));
//        builder.append("<h3>Customer inserted successfully</h3>");
//        builder.append("<p><a href='InsertCustomer.html'>Go Back</a></p>");
//        builder.append("</body>")
//                .append("</html>");
//        return builder.toString();
    }

    public String getStringCustomers() {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader("Customers"));

        builder.append(getTable("Customers", getCustomers()));
        builder.append("</body>")
                .append("</html>");
        return builder.toString();
    }

    public String getTable(String title, List<Customer> customers) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table border=\"1\">")
                .append("<caption>")
                .append(title)
                .append("</caption>");
        for (Customer c : customers) {
            builder.append("<tr>")
                    .append("<td>").append(c.getCcode()).append("</td>")
                    .append("<td>").append(c.getCname()).append("</td>")
                    .append("</tr>");
        }
        return builder.toString();
    }
    
    public String getHeader(String title){
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>").append(title).append("</title>")
                .append("<body>");
        return builder.toString();
    }
}

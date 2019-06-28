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

    public CustomerService(){
        
    }
    
    public List<Customer> getCustomers() {
        CustomerDao cdao = new CustomerDao();
        return cdao.getCustomers();
    }
    
    public String getCustomerUpdateFormById(int ccode){
        CustomerDao cdao = new CustomerDao();
        Customer c = cdao.getCustomerById(ccode);
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader("Edit Customer"));
        builder.append(getCustomerUpdateForm(c));
        builder.append("</body>")
                .append("</html>");
        return builder.toString();
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
    
    public boolean saveCustomer(Customer c){
        CustomerDao cdao = new CustomerDao();
        return cdao.updateCustomer(c);
    }
    
    public boolean deleteCustomerById(int id){
        CustomerDao cdao = new CustomerDao();
        boolean deleted = cdao.deleteCustomerById(id);
        return deleted;
    }

    public String getStringCustomers() {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader("Customers"));

        builder.append(getTable("Customers", getCustomers()));
        builder.append("</body>")
                .append("</html>");
        return builder.toString();
    }
    
    private String getCustomerUpdateForm(Customer c){
        StringBuilder builder = new StringBuilder();
        builder.append("<form action='UpdateCustomer' method='POST'>");
        builder.append("<p><b>Ccode:</b>").append("<input type='text' name='ccode' readonly value='").append(c.getCcode()).append("'></p>")
                .append("<p><b>Cname:</b>").append("<input type='text' name='cname' value='")
                .append(c.getCname()).append("'></p>");
        builder.append("<input type='submit' value='Save'>");
        builder.append("</form>");
        return builder.toString();
    }

    private String getTable(String title, List<Customer> customers) {
        StringBuilder builder = new StringBuilder();
        builder.append("<table border=\"1\">")
                .append("<caption>")
                .append(title)
                .append("</caption>");
        for (Customer c : customers) {
            builder.append("<tr>")
                    .append("<td>").append(c.getCcode()).append("</td>")
                    .append("<td>").append(c.getCname()).append("</td>")
                    .append("<td>").append("<a href='http://localhost:8080/ServletExamples/UpdateCustomer?method=update&id=").append(c.getCcode()).append("'>Edit</a>").append("</td>")
                    .append("<td>").append("<a href='http://localhost:8080/ServletExamples/Customers?method=delete&id=").append(c.getCcode()).append("'>Delete</a>").append("</td>")
                    .append("</tr>");
        }
        return builder.toString();
    }
    
    private String getHeader(String title){
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>").append(title).append("</title>")
                .append("<body>");
        return builder.toString();
    }
}

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

    public CustomerService() {

    }

    public List<Customer> getCustomers() {
        CustomerDao cdao = new CustomerDao();
        return cdao.getCustomers();
    }
    
    private Customer getCustomerById(int id){
        CustomerDao cdao = new CustomerDao();
        return cdao.getCustomerById(id);
    }

    public String getCustomerUpdateFormById(int ccode) {
        CustomerDao cdao = new CustomerDao();
        Customer c = cdao.getCustomerById(ccode);
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader("Edit Customer"));
        builder.append(getCustomerUpdateForm(c));
        builder.append("</body>")
                .append("</html>");
        return builder.toString();
    }

    public boolean insert(Customer c) {
        CustomerDao cdao = new CustomerDao();
        if (cdao.insertCustomer(c)) {
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

    public boolean saveCustomer(Customer c) {
        CustomerDao cdao = new CustomerDao();
        return cdao.updateCustomer(c);
    }

    public boolean deleteCustomerById(int id) {
        CustomerDao cdao = new CustomerDao();
        boolean deleted = cdao.deleteCustomerById(id);
        return deleted;
    }

    public String getStringCustomers() {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader("Customers"))
        .append(getTable("Customers", getCustomers()))
        .append("<br/>").append("<p><a href='InsertCustomer'>New Customer</a></p>")
        .append("<p><a href='home.html'>Home</a></p>")
        .append("</body>")
                .append("</html>");
        return builder.toString();
    }

    public String getCustomerInsertForm() {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader("Insert Customer"))
                .append("<form action='InsertCustomer' method='POST'>")
                .append("<h3>Insert Customer Info</h3><br>")
                .append("<p><b>Ccode:</b>").append("<input type='text' name='ccode'></p>")
                .append("<p><b>Cname:</b>").append("<input type='text' name='cname'></p>")
                .append("<input type='submit' value='Save'>")
                .append("</form>")
                .append("</body>")
                .append("</html>");
        return builder.toString();

    }

    private String getCustomerUpdateForm(Customer c) {
        StringBuilder builder = new StringBuilder();
        builder.append("<form action='UpdateCustomer' method='POST'>");
        builder.append("<p><b>Ccode:</b>").append("<input type='text' name='ccode' readonly value='").append(c.getCcode()).append("'></p>")
                .append("<p><b>Cname:</b>").append("<input type='text' name='cname' value='")
                .append(c.getCname()).append("'></p>");
        builder.append("<input type='submit' value='Save'>");
        builder.append("</form>");
        return builder.toString();
    }

    public String getCustomerViewForm(int id) {
        Customer c = getCustomerById(id);
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader("View Customer"))
                .append("<table border=\"1\">")
                .append("<caption>").append("Customer Info").append("</caption>")
                .append("<tr>")
                .append("<td>").append("<b>Ccode</b>").append("</td>").append("<td>").append(c.getCcode()).append("</td>")
                .append("</tr>")
                .append("<tr>")
                .append("<td>").append("<b>Cname</b>").append("</td>").append("<td>").append(c.getCname()).append("</td>")
                .append("</tr>")
                .append("</table>")
                //.append("<br/>")
                .append("<p><a href='Customers'>Go Back</a></p>")
                .append("</body>")
                .append("</html>");
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
                    .append("<td>").append("<a href='http://localhost:8080/ServletExamples/ViewCustomer?id=").append(c.getCcode()).append("'>View</a>").append("</td>")
                    .append("<td>").append("<a href='http://localhost:8080/ServletExamples/UpdateCustomer?method=update&id=").append(c.getCcode()).append("'>Edit</a>").append("</td>")
                    .append("<td>").append("<a href='http://localhost:8080/ServletExamples/DeleteCustomer?method=delete&id=").append(c.getCcode()).append("'>Delete</a>").append("</td>")
                    .append("</tr>");

        }
        builder.append("</table>");
        return builder.toString();
    }

    private String getHeader(String title) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<head>")
                .append("<title>").append(title).append("</title>")
                .append("<body>");
        return builder.toString();
    }
}

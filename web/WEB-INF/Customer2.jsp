<%-- 
    Document   : Customer
    Created on : Jun 25, 2019, 10:25:16 AM
    Author     : tasos
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Customer"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of Customers</h1>
        <table border ="1" width="500" align="center"> 
         <tr bgcolor="00FF7F"> 
          <th><b>Ccode</b></th> 
          <th><b>Name</b></th> 
         </tr> 
        <%-- Fetching the attributes of the request object 
             which was previously set by the servlet  
              "StudentServlet.java" 
        --%>  
        <%List<Customer> list =  
            (List<Customer>)request.getAttribute("customers"); 
        for(Customer c:list){%> 
        <%-- Arranging data in tabular form 
        --%> 
            <tr> 
                <td><a href="${pageContext.request.contextPath}/customer?id=${book.id}"><%=c.getCcode()%></a></td> 
                <td><%=c.getCname()%></td> 
            </tr> 
            <%}%> 
        </table>  
    </body>
</html>

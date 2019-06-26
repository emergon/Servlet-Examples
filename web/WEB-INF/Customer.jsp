<%-- 
    Document   : Customer
    Created on : Jun 25, 2019, 10:25:16 AM
    Author     : tasos
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Listing</title>
    </head>
    <body>


        <table>
            <tr>
                <th>Code</th>
                <th>Name</th>
            </tr>

            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.ccode}</td>
                    <td>${customer.cname}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
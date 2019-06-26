<%-- 
    Document   : Date
    Created on : Jun 25, 2019, 10:47:24 AM
    Author     : tasos
--%>

<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        3+2=<%=3 + 2%><br/>
        3==3=<%=3 == 3%><br/>
        println = <% out.println(5);%><br/>
        <p>Today is <%=new Date()%></p>
        <%double a;%>
        <%a = Math.random();%>
        <%
            if (a < 0.5) {
                out.println("a =" + a + ". It is less that 0.5");
            } else {
                out.println("a =" + a + ". It is more that 0.5");
            }
        %><br/>

        <%--Comments like this can be multi line
            Here is another line
        --%>
        <%-- ! is for declaration--%>


        <%--
            This is valid because jasper transforms to servlet. 
            All the code goes into the init() method of servlet.
            It works even if it is put after the <html> tag
        --%>
        <%out.print("count value is " + count);%>
        <%! public static int count = 15;%>

    </body>
</html>

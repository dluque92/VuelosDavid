<%-- 
    Document   : listarPaises
    Created on : 04-may-2017, 19:11:25
    Author     : david
--%>

<%@page import="java.util.List"%>
<%@page import="vuelos.entity.Country"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Country> listaPaises = (List<Country>) request.getAttribute("listaPaises");
%>

<!DOCTYPE html>
<html>
    <head>
        <link href='style.css' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ServletListarVuelos" method="POST"> 
            <%
                for (Country pais : listaPaises) {
            %>
            <ul>
                <li>
                    <input type="checkbox" value="<%=pais.getCountryIsoCode()%>" name="pais"><%=pais.getCountry()%>
                </li>
            </ul>
            <%
                }
            %>
            <input type="submit" value="Buscar">
        </form>
    </body>
</html>

<%-- 
    Document   : listarVuelos
    Created on : 05-may-2017, 10:58:43
    Author     : david
--%>

<%@page import="vuelos.entity.City"%>
<%@page import="vuelos.entity.Country"%>
<%@page import="java.util.List"%>
<%@page import="vuelos.entity.Flight"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Flight> listaVuelos = (List<Flight>) session.getAttribute("listaVuelos");
    List<City> listaCiudades = (List<City>) request.getAttribute("listaCiudades");

    String idVuelo = (String) request.getAttribute("id");
%>

<!DOCTYPE html>
<html>
    <head>
        <link href='style.css' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Flight_ID</th>
                <th>Segment_Number</th>
                <th>ORIG_AIRPORT</th>
                <th>DEPART_TIME</th>
                <th>DEST_AIRPORT</th>
                <th>ARRIVE_TIME</th>
                <th>MEAL</th>
                <th>FLYING_TIME</th>
                <th>MILES</th>
                <th>AIRCRAFT</th>
                <th></th>
                <th></th>
            </tr>
            <%
                for (Flight vuelo : listaVuelos) {
                    if (vuelo.getFlightPK().getFlightId().equals(idVuelo) && idVuelo != null) {
            %>
            <form action="ServletGuardarVuelos" method="POST">
                <tr>
                    <td><%=vuelo.getFlightPK().getFlightId()%></td>
                    <td><%=vuelo.getFlightPK().getSegmentNumber()%></td>
                <input type="text" id="idVuelo" name="idVuelo" value="<%=vuelo.getFlightPK().getFlightId()%>" hidden="true">
                <input type="text" id="segmentNumber" name="segmentNumber" value="<%=vuelo.getFlightPK().getSegmentNumber()%>" hidden="true">
                <td>
                    <select name="aeroOrigen" id="aeroOrigen">
                        <%
                            for (City ciudad : listaCiudades) {
                                if (ciudad.getAirport() == vuelo.getOrigAirport().getAirport()) {
                        %>
                        <option selected="true"><%=ciudad.getAirport()%></option>
                        <%
                        } else {
                        %>
                        <option><%=ciudad.getAirport()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </td>
                <td><input type="time" name="departTime" id="departTime" value="<%=vuelo.getDepartTimeString()%>"></td>
                <td>
                    <select id="aeroDestino" name="aeroDestino">
                        <%
                            for (City ciudad : listaCiudades) {
                                if (ciudad.getAirport() == vuelo.getDestAirport().getAirport()) {
                        %>
                        <option selected="true"><%=ciudad.getAirport()%></option>
                        <%
                        } else {
                        %>
                        <option><%=ciudad.getAirport()%></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </td>
                <td><input type="time" name="arriveTime" id="arriveTime" value="<%=vuelo.getArriveTimeString()%>"></td>
                <td><input type="text" name="meal" id="meal" size="1" maxlength="1" value="<%=vuelo.getMeal()%>"></td>
                <td><input type="number" name="flyingTime" id="flyingTime" value="<%=vuelo.getFlyingTime()%>"></td>
                <td><input type="number" name="miles" id="miles" value="<%=vuelo.getMiles()%>"></td>
                <td><input type="text" name="aircraft" id="aircraft" value="<%=vuelo.getAircraft()%>"></td>
                <td><input type="submit" value="Guardar"></td>

                </tr>
            </form>
            <%
            } else {
            %>
            <tr>
                <td><%=vuelo.getFlightPK().getFlightId()%></td>
                <td><%=vuelo.getFlightPK().getSegmentNumber()%></td>
                <td><%=vuelo.getOrigAirport().getAirport()%></td>
                <td><%=vuelo.getDepartTime()%></td>
                <td><%=vuelo.getDestAirport().getAirport()%></td>
                <td><%=vuelo.getArriveTime()%></td>
                <td><%=vuelo.getMeal()%></td>
                <td><%=vuelo.getFlyingTime()%></td>
                <td><%=vuelo.getMiles()%></td>
                <td><%=vuelo.getAircraft()%></td>
                <td><a href="ServletEditarVuelo?id=<%=vuelo.getFlightPK().getFlightId()%>">Editar</a></td>
                <td><a href="ServletBorrarVuelo?id=<%=vuelo.getFlightPK().getFlightId()%>&seg=<%=vuelo.getFlightPK().getSegmentNumber()%>">Borrar</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>  
    </body>
</html>
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuelos.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuelos.ejb.CityFacade;
import vuelos.ejb.FlightFacade;
import vuelos.entity.City;
import vuelos.entity.Flight;

/**
 *
 * @author david
 */
@WebServlet(name = "ServletGuardarVuelos", urlPatterns = {"/ServletGuardarVuelos"})
public class ServletGuardarVuelos extends HttpServlet {

    @EJB
    private CityFacade cityFacade;

    @EJB
    private FlightFacade flightFacade;

    
    
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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        String idVuelo = request.getParameter("idVuelo");
        String segmentNumber = request.getParameter("segmentNumber");
        String aeroOrigen = request.getParameter("aeroOrigen");
        String departTime = request.getParameter("departTime");
        String aeroDestino = request.getParameter("aeroDestino");
        String arriveTime = request.getParameter("arriveTime");
        String meal = request.getParameter("meal");
        String flyingTime = request.getParameter("flyingTime");
        String miles = request.getParameter("miles");
        String aircraft = request.getParameter("aircraft");
        
        Flight vuelo = flightFacade.buscarPorId(idVuelo, segmentNumber);
        City ciudadOrigen = cityFacade.searchCityByAirport(aeroOrigen);
        vuelo.setOrigAirport(ciudadOrigen);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        vuelo.setDepartTime(sdf.parse(departTime));
        City ciudadDestino = cityFacade.searchCityByAirport(aeroDestino);
        vuelo.setDestAirport(ciudadDestino);
        vuelo.setArriveTime(sdf.parse(arriveTime));
        vuelo.setMeal(meal.charAt(0));
        vuelo.setFlyingTime(Double.parseDouble(flyingTime));
        vuelo.setMiles(Integer.parseInt(miles));
        vuelo.setAircraft(aircraft);
        
        this.flightFacade.edit(vuelo);
        
        String id = null;
        request.setAttribute("id", id);
        
        List<Flight> listaVuelos;
        HttpSession session = request.getSession();
        List listaPaises = (List) session.getAttribute("listaPaises");
         
        if (listaPaises == null) {
            listaVuelos = (List) flightFacade.findAll();
            request.setAttribute("listaVuelos", listaVuelos);
        } else {
            listaVuelos = (List) flightFacade.listarVuelos(Arrays.asList(listaPaises));
            request.setAttribute("listaVuelos", listaVuelos);
        }

        session.setAttribute("listaPaises", listaPaises);
        session.setAttribute("listaVuelos",listaVuelos);
        
        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/listarVuelos.jsp");
        rd.forward(request, response);
        
        
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletGuardarVuelos.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletGuardarVuelos.class.getName()).log(Level.SEVERE, null, ex);
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

}

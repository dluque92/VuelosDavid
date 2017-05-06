/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuelos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vuelos.ejb.FlightFacade;
import vuelos.entity.Flight;

/**
 *
 * @author david
 */
@WebServlet(name = "ServletBorrarVuelo", urlPatterns = {"/ServletBorrarVuelo"})
public class ServletBorrarVuelo extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String stringId = request.getParameter("id");
        String segmentNumber = request.getParameter("seg");
        request.setAttribute("id", null);

        Flight vuelo = flightFacade.buscarPorId(stringId, segmentNumber);

        this.flightFacade.remove(vuelo);

        String id = null;
        request.setAttribute("id", id);

        List<Flight> listaVuelos;
        List listaPaises = (List) session.getAttribute("listaPaises");

        if (listaPaises == null) {
            listaVuelos = (List) flightFacade.findAll();
            request.setAttribute("listaVuelos", listaVuelos);
        } else {
            listaVuelos = (List) flightFacade.listarVuelos(Arrays.asList(listaPaises));
            request.setAttribute("listaVuelos", listaVuelos);
        }

        session.setAttribute("listaPaises", listaPaises);
        session.setAttribute("listaVuelos", listaVuelos);

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
        processRequest(request, response);
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

}

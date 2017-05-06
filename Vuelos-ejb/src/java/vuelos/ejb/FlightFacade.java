/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuelos.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import vuelos.entity.Flight;

/**
 *
 * @author david
 */
@Stateless
public class FlightFacade extends AbstractFacade<Flight> {

    @PersistenceContext(unitName = "Vuelos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlightFacade() {
        super(Flight.class);
    }
    
    public List listarVuelos(List paises) {
        Query q;
        q = this.em.createQuery("SELECT f FROM Flight f WHERE f.origAirport.countryIsoCode IN :origAirport ");
        q.setParameter("origAirport", paises);
        return (List)q.getResultList();
    }
    
    public Flight buscarPorId(String idVuelo, String segmentNumber){
        Query q;
        q = this.em.createQuery("SELECT f FROM Flight f WHERE f.flightPK.flightId = :flightId AND f.flightPK.segmentNumber = :segment");
        q.setParameter("flightId", idVuelo);
        q.setParameter("segment", Integer.parseInt(segmentNumber));
        return (Flight) q.getSingleResult();
    } 
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuelos.ejb;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import vuelos.entity.City;

/**
 *
 * @author david
 */
@Stateless
public class CityFacade extends AbstractFacade<City> {

    @PersistenceContext(unitName = "Vuelos-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CityFacade() {
        super(City.class);
    }
    
    public City searchCityByAirport(String airport){
        Query q;
        q = this.em.createQuery("SELECT c FROM City c WHERE c.airport = :airport");
        q.setParameter("airport", airport);
        return (City) q.getSingleResult();
    }
    
}

package vuelos.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vuelos.entity.Country;
import vuelos.entity.Flight;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2017-05-05T20:19:29")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, String> country;
    public static volatile SingularAttribute<City, String> cityName;
    public static volatile SingularAttribute<City, Country> countryIsoCode;
    public static volatile CollectionAttribute<City, Flight> flightCollection1;
    public static volatile SingularAttribute<City, String> language;
    public static volatile CollectionAttribute<City, Flight> flightCollection;
    public static volatile SingularAttribute<City, String> airport;

}
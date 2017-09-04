package database.entities;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="Cities")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cities {

    @XmlElement(name = "City")
    private List<City> cities = null;

    @XmlElement(name = "Distance")
    private List<Distance> distances = null;

    public List<City> getCities() {
        return cities;
    }

    public List<Distance> getDistances() {
        return distances;
    }
}

package database.entities;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="City")
@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlElement(name="Name")
    private String name;

    @XmlElement(name="Latitude")
    private String latitude;

    @XmlElement(name="Longitude")
    private String longitude;

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}

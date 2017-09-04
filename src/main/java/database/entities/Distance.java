package database.entities;


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="Distance")
@XmlAccessorType(XmlAccessType.FIELD)
public class Distance {

    @XmlElement(name="From")
    private String fromCity;

    @XmlElement(name="To")

    private String toCity;

    @XmlElement(name="Dist")
    private int distance;

    public String getFromCity() {
    return fromCity;
  }
    public String getToCity() {
    return toCity;
  }
    public int getDistance() {
    return distance;
  }

}

package ca.concordia.soen487.lab5.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", propOrder = {
        "buildingAndStreet",
        "city",
        "zip"
})
public class Address {

    private String buildingAndStreet;
    private String city;
    private String zip;

    public Address() {
    }

    public String getBuildingAndStreet() {
        return buildingAndStreet;
    }

    public void setBuildingAndStreet(String buildingAndStreet) {
        this.buildingAndStreet = buildingAndStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "buildingAndStreet='" + buildingAndStreet + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}

package database;

import database.entities.Cities;
import database.exceptions.NoCityInDatabase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.SQLException;

/**
 * алоадер данных
 */
public class DataUploader {

    public static boolean upload(File file)  {
        try {
            JAXBContext jc = JAXBContext.newInstance(Cities.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Cities cities = (Cities) unmarshaller.unmarshal(file);
            Database db = Database.getInstance();
            db.clear();
            db.insertCities(cities.getCities());
            db.insertDistances(cities.getDistances());
        } catch (JAXBException | SQLException | NoCityInDatabase e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

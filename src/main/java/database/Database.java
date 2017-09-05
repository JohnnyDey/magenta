package database;

import database.entities.City;
import database.entities.Distance;
import database.exceptions.NoCityInDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * подключение к базе данных
 */
public class Database {
    private static Database instance;
    private static final String url = "jdbc:mysql://localhost:3306/distance-calculator?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private Database() {
        open();
    }

    public static Database getInstance() {
        if(instance == null) instance = new Database();
        return instance;
    }

    /**
     * геттеры и сеттеры данных
     * @throws SQLException
     */
    public List<String> getCities() throws SQLException {
        List<String> list = new ArrayList<String>();
        String query = "SELECT ID, name FROM city";
        rs = stmt.executeQuery(query);
        while (rs.next()){
            list.add(rs.getInt(1) + ":" + rs.getString(2));
        }
        return list;
    }

    public String getDistance(String city1, String city2) throws SQLException, NoCityInDatabase {
        int id1 = getCityIdByName(city1);
        int id2 = getCityIdByName(city2);
        String query = "SELECT distance FROM distance WHERE (fromCity = " + id1 + " AND toCity = " + id2 + ") OR  " +
                        "(fromCity = " + id2 + " AND toCity = " + id1 + ")";
        rs = stmt.executeQuery(query);
        if(rs.next()) return rs.getString(1);
        else throw new NoCityInDatabase();

    }

    public String[] getPos(String city) throws SQLException, NoCityInDatabase {
        String query = "SELECT latitude, longitude FROM city WHERE name = '" + city + "'";
        rs = stmt.executeQuery(query);
        String[] res = new String[2];
        if(rs.next()) {
            res[0] = rs.getString(1);
            res[1] = rs.getString(2);
            return res;
        }
        else throw new NoCityInDatabase();
    }

    private int getCityIdByName(String city) throws SQLException, NoCityInDatabase {
        rs = stmt.executeQuery("SELECT ID FROM city WHERE name ='" + city + "'");
        if(rs.next()) return rs.getInt(1);
        else throw new NoCityInDatabase();
    }

    public void insertCities(List<City> cities) throws SQLException {
        String query = "INSERT INTO city (name, latitude, longitude) VALUES ";
        for(int i = 0; i < cities.size(); i++){
            City c = cities.get(i);
            query += "(\"" + c.getName() + "\", \"" + c.getLatitude() + "\", \"" + c.getLongitude() + "\")";
            query += i + 1 == cities.size() ? ";" : ",";
        }
        stmt.execute(query);
    }

    public void insertDistances(List<Distance> distances) throws SQLException, NoCityInDatabase {
        String query = "INSERT INTO distance (fromCity, toCity, distance) VALUES ";
        for(int i = 0; i < distances.size(); i++){
            Distance d = distances.get(i);
            query += "('" + getCityIdByName(d.getFromCity()) + "', '" + getCityIdByName(d.getToCity()) + "', '" + d.getDistance() + "')";
            query += i + 1 == distances.size() ? ";" : ",";
        }
        stmt.execute(query);
    }
    public void clear() throws SQLException {
        String query = "TRUNCATE TABLE city";
        stmt.execute(query);
        query = "TRUNCATE TABLE distance;";
        stmt.execute(query);
    }

    /** открытие/закрытие */
    public void open(){
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void close(){
        try {
            con.close();
        } catch (SQLException se) { }
        try {
            stmt.close();
        } catch (SQLException se) {  }
        try {
            rs.close();
        } catch (SQLException se) { }
    }
}

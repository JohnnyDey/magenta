package calculator;

import database.Database;
import database.exceptions.NoCityInDatabase;

import java.sql.SQLException;

public class Calculator {

    public static String getDistance(String from, String to){
        try {
            return Database.getInstance().getDistance(from, to);
        } catch (SQLException e) {
            return "No database connection";
        } catch (NoCityInDatabase e){
            return "Have no info about this city";
        }
    }
    public static String getFlightDistance(String from, String to){
        try {
            String[] city1 = Database.getInstance().getPos(from);
            String[] city2 = Database.getInstance().getPos(to);
            double l1 = sin(parseToGrades(city1[0])) * sin(parseToGrades(city2[0]));
            double l2 = cos(parseToGrades(city1[0])) * cos(parseToGrades(city2[0])) * cos(parseToGrades(city1[1]) - parseToGrades(city2[1]));
            return String.valueOf(round(Math.acos(l1 + l2) * 6371));
        } catch (SQLException e) {
            return "No database connection";
        }catch (NoCityInDatabase e){
            return "Have no info about this city";
        }
    }
    private static double parseToGrades(String val){
        double grade = Double.parseDouble(val.substring(0, val.indexOf("*")));
        grade += Double.parseDouble(val.substring(val.indexOf("*") + 1, val.indexOf("'"))) * 1/60;
        grade += Double.parseDouble(val.substring(val.indexOf("'") + 1, val.indexOf("''"))) * 1/3600;
        return (val.endsWith("n") || val.endsWith("w")) ? -grade : grade;
    }
    private static double sin(double d){
        return Math.sin(Math.toRadians(d));
    }
    private static double cos(double d){
        return Math.cos(Math.toRadians(d));
    }
    private static double round(double d){
        d = d * 100;
        int i = (int) Math.round(d);
        return (double)i / 100;
    }
}

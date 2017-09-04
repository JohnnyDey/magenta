package pages;


import database.Database;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.SQLException;

@Path("/cities")
public class Cities {
    @GET
    public String get(){
        try {
            return Database.getInstance().getCities().toString();
        } catch (SQLException e) {
            return "No database connection";
        }
    }
}

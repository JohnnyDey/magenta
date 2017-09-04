package pages;


import database.Database;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * magenta/rest/cities/ - получает список всех городов с их идентификаторами
 */
@Path("/cities")
public class Cities {
    @GET
    public Response get(){
        try {
            return Response.status(200).entity(Database.getInstance().getCities().toString()).build();
        } catch (SQLException e) {
            return Response.status(400).build();
        }
    }
}

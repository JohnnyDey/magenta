package pages;

import calculator.Calculator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * magenta/rest/distance/{from}/{to} - расстояние из базы
 * magenta/rest/crow/{from}/{to} - расчитанное расстояние
 * magenta/rest/both/{from}/{to} - оба
 */

@Path("/calc")
public class Distance {
    @GET
    @Path("distance/{from}/{to}/")
    public Response getDist(@PathParam("from") String from, @PathParam("to") String to){
        return Response.status(200).entity(Calculator.getDistance(from, to)).build();
    }
    @POST
    @Path("distance/{from}/{to}/")
    public Response postDist(@PathParam("from") String from, @PathParam("to") String to){
        return Response.status(200).entity(Calculator.getDistance(from, to)).build();
    }
    @GET
    @Path("crow/{from}/{to}/")
    public Response getCrow(@PathParam("from") String from, @PathParam("to") String to){
        return Response.status(200).entity(Calculator.getFlightDistance(from, to)).build();
    }
    @POST
    @Path("crow/{from}/{to}/")
    public Response postCrow(@PathParam("from") String from, @PathParam("to") String to){
        return Response.status(200).entity(Calculator.getFlightDistance(from, to)).build();
    }
    @GET
    @Path("both/{from}/{to}/")
    public Response getBoth(@PathParam("from") String from, @PathParam("to") String to){
        String res = Calculator.getFlightDistance(from, to) + ", " + Calculator.getDistance(from, to);
        return Response.status(200).entity(res).build();
    }
    @POST
    @Path("both/{from}/{to}/")
    public Response postBoth(@PathParam("from") String from, @PathParam("to") String to){
        String res = Calculator.getFlightDistance(from, to) + ", " + Calculator.getDistance(from, to);
        return Response.status(200).entity(res).build();
    }
}

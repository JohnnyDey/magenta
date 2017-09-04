package pages;

import calculator.Calculator;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Path("/calc")
public class Distance {
    @GET
    @Path("distance/{from}/{to}/")
    public String getDist(@PathParam("from") String from, @PathParam("to") String to){
        return Calculator.getDistance(from, to);
    }
    @POST
    @Path("distance/{from}/{to}/")
    public String postDist(@PathParam("from") String from, @PathParam("to") String to){
        return Calculator.getDistance(from, to);
    }
    @GET
    @Path("crow/{from}/{to}/")
    public String getCrow(@PathParam("from") String from, @PathParam("to") String to){
        return Calculator.getFlightDistance(from, to);
    }
    @POST
    @Path("crow/{from}/{to}/")
    public String postCrow(@PathParam("from") String from, @PathParam("to") String to){
        return Calculator.getFlightDistance(from, to);
    }
    @GET
    @Path("both/{from}/{to}/")
    public String getBoth(@PathParam("from") String from, @PathParam("to") String to){
        String res = Calculator.getFlightDistance(from, to) + ", " + Calculator.getDistance(from, to);
        return res;
    }
    @POST
    @Path("both/{from}/{to}/")
    public String postBoth(@PathParam("from") String from, @PathParam("to") String to){
        String res = Calculator.getFlightDistance(from, to) + ", " + Calculator.getDistance(from, to);
        return res;
    }
}

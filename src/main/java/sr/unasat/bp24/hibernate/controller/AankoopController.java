package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.entity.Aankoop;
import sr.unasat.bp24.hibernate.service.AankoopService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/aankopen")
public class AankoopController {

    private final AankoopService aankoopService = new AankoopService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAankopen() {
        return Response.ok(aankoopService.getAankopen()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAankoop(Aankoop aankoop) {
        Aankoop createdAankoop = aankoopService.createAankoop(aankoop);
        return Response.status(Response.Status.CREATED).entity(createdAankoop).build();
    }

    // Implementeer andere methoden zoals GET by ID, UPDATE, DELETE indien nodig
}

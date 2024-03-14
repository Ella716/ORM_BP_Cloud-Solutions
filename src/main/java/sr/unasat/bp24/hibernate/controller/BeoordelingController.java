package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.entity.Beoordeling;
import sr.unasat.bp24.hibernate.service.BeoordelingService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/beoordelingen")
public class BeoordelingController {

    private final BeoordelingService beoordelingService = new BeoordelingService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBeoordelingen() {
        return Response.ok(beoordelingService.getBeoordelingen()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBeoordeling(Beoordeling beoordeling) {
        Beoordeling createdBeoordeling = beoordelingService.createBeoordeling(beoordeling);
        return Response.status(Response.Status.CREATED).entity(createdBeoordeling).build();
    }

    // Implementeer andere methoden zoals GET by ID, UPDATE, DELETE indien nodig
}

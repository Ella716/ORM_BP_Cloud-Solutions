package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.entity.SpecialeAanbieding;
import sr.unasat.bp24.hibernate.service.SpecialeAanbiedingService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/specialeaanbiedingen")
public class SpecialeAanbiedingController {

    private final SpecialeAanbiedingService aanbiedingService = new SpecialeAanbiedingService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSpecialeAanbieding(SpecialeAanbieding specialeAanbieding) {
        SpecialeAanbieding createdAanbieding = aanbiedingService.createSpecialeAanbieding(specialeAanbieding);
        return Response.status(Response.Status.CREATED).entity(createdAanbieding).build();
    }

    // Implementeer andere methoden zoals GET by ID, UPDATE, DELETE indien nodig
}

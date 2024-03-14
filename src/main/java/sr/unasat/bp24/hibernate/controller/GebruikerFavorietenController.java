package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.entity.GebruikerFavorieten;
import sr.unasat.bp24.hibernate.service.GebruikerFavorietenService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/gebruikerfavorieten")
public class GebruikerFavorietenController {

    private final GebruikerFavorietenService favorietenService = new GebruikerFavorietenService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGebruikerFavorieten(GebruikerFavorieten gebruikerFavorieten) {
        GebruikerFavorieten createdFavorieten = favorietenService.createGebruikerFavorieten(gebruikerFavorieten);
        return Response.status(Response.Status.CREATED).entity(createdFavorieten).build();
    }

    // Implementeer andere methoden zoals GET by ID, UPDATE, DELETE indien nodig
}

package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.entity.Gebruiker;
import sr.unasat.bp24.hibernate.service.GebruikerService;
import sr.unasat.bp24.hibernate.dto.GebruikerDTO;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/gebruikers")
public class GebruikerController {

    private final GebruikerService gebruikerService = GebruikerService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGebruikers() {
        return Response.ok(gebruikerService.getGebruikers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGebruikerById(@PathParam("id") int id) {
        Gebruiker gebruiker = gebruikerService.getGebruikerById(id);
        if (gebruiker != null) {
            return Response.ok(gebruiker).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGebruiker(Gebruiker gebruiker) {
        Gebruiker createdGebruiker = gebruikerService.createGebruiker(gebruiker);
        return Response.status(Response.Status.CREATED).entity(createdGebruiker).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGebruiker(@PathParam("id") int id, GebruikerDTO gebruikerDTO) {
        Gebruiker existingGebruiker = gebruikerService.getGebruikerById(id);
        if (existingGebruiker == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingGebruiker.setNaam(gebruikerDTO.getNaam());
        existingGebruiker.setEmail(gebruikerDTO.getEmail());
        // Update other properties as needed

        gebruikerService.updateGebruiker(existingGebruiker);
        return Response.ok(existingGebruiker).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteGebruiker(@PathParam("id") int id) {
        Gebruiker gebruiker = gebruikerService.getGebruikerById(id);
        if (gebruiker != null) {
            gebruikerService.deleteGebruiker(gebruiker);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

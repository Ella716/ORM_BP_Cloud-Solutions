package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.entity.Categorie;
import sr.unasat.bp24.hibernate.service.CategorieService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/categorieen")
public class CategorieController {

    private final CategorieService categorieService = new CategorieService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategorieen() {
        return Response.ok(categorieService.getCategorieen()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategorie(String categorieNaam) {
        Categorie createdCategorie = categorieService.createCategorie(categorieNaam);
        return Response.status(Response.Status.CREATED).entity(createdCategorie).build();
    }

    @PUT
    @Path("/{categorieId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategorie(@PathParam("categorieId") int categorieId, String newNaam) {
        Categorie updatedCategorie = categorieService.updateCategorie(categorieId, newNaam);
        if (updatedCategorie != null) {
            return Response.ok(updatedCategorie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{categorieId}")
    public Response deleteCategorie(@PathParam("categorieId") int categorieId) {
        boolean isDeleted = categorieService.deleteCategorie(categorieId);
        if (isDeleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}

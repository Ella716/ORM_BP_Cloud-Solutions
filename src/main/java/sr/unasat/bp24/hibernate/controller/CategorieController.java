package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.dto.CategorieDTO;
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorieById(@PathParam("id") int id) {
        Categorie categorie = categorieService.getCategorieById(id);
        if (categorie != null) {
            return Response.ok(categorie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategorie(String categorieNaam) {
        Categorie createdCategorie = categorieService.createCategorie(categorieNaam);
        return Response.status(Response.Status.CREATED).entity(createdCategorie).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategorie(@PathParam("id") int id, CategorieDTO categorieDTO) {
        Categorie existingCategorie = categorieService.getCategorieById(id);
        if (existingCategorie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingCategorie.setNaam(categorieDTO.getNaam());
        // Update other properties as needed

        categorieService.updateCategorie(existingCategorie);
        return Response.ok(existingCategorie).build();
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

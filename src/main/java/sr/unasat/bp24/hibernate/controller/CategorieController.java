package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.dto.CategorieDTO;
import sr.unasat.bp24.hibernate.entity.Categorie;
import sr.unasat.bp24.hibernate.service.CategorieService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/categorieen")
public class CategorieController {

    private final CategorieService categorieService = new CategorieService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategorieDTO> getCategorieen() {
        List<Categorie> categorieen = categorieService.getCategorieen();
        List<CategorieDTO> categorieDTOs = new ArrayList<>();
        for (Categorie categorie : categorieen) {
            CategorieDTO categorieDTO = new CategorieDTO();
            categorieDTO.setCategorieId(categorie.getCategorieId());
            categorieDTO.setNaam(categorie.getNaam());
            categorieDTOs.add(categorieDTO);
        }
        return categorieDTOs;
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorieById(@PathParam("id") int id) {
        Categorie categorie = categorieService.getCategorieById(id);
        if (categorie != null) {
            CategorieDTO categorieDTO = new CategorieDTO();
            categorieDTO.setCategorieId(categorie.getCategorieId());
            categorieDTO.setNaam(categorie.getNaam());
            return Response.ok(categorieDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategorie(String categorieNaam) {
        Categorie createdCategorie = categorieService.createCategorie(categorieNaam);
        CategorieDTO createdCategorieDTO = new CategorieDTO();
        createdCategorieDTO.setCategorieId(createdCategorie.getCategorieId());
        createdCategorieDTO.setNaam(createdCategorie.getNaam());
        return Response.status(Response.Status.CREATED).entity(createdCategorieDTO).build();
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
        CategorieDTO updatedCategorieDTO = new CategorieDTO();
        updatedCategorieDTO.setCategorieId(existingCategorie.getCategorieId());
        updatedCategorieDTO.setNaam(existingCategorie.getNaam());
        return Response.ok(updatedCategorieDTO).build();
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

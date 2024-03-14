package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.entity.Product;
import sr.unasat.bp24.hibernate.service.ProductService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/producten")
public class ProductController {

    private final ProductService productService = new ProductService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        Product createdProduct = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(createdProduct).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response zoekProductenOpNaam(@QueryParam("naam") String naam) {
        if (naam == null || naam.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Naam parameter is vereist").build();
        }
        return Response.ok(productService.zoekProductenOpNaam(naam)).build();
    }

    // Implementeer andere methoden zoals GET by ID, UPDATE, DELETE indien nodig
}

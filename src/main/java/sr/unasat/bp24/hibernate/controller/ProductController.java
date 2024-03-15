package sr.unasat.bp24.hibernate.controller;

import sr.unasat.bp24.hibernate.dto.ProductDTO;
import sr.unasat.bp24.hibernate.entity.Product;
import sr.unasat.bp24.hibernate.service.ProductService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/producten")
public class ProductController {

    private final ProductService productService = new ProductService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProductsWithCategory() {
        List<ProductDTO> products = productService.getAllProductsWithCategory();
        return Response.ok(products).build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return Response.ok(product).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        Product createdProduct = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(createdProduct).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, ProductDTO productDTO) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingProduct.setNaam(productDTO.getNaam());
        existingProduct.setPrijs(productDTO.getPrijs());
        // Update other properties as needed

        productService.updateProduct(existingProduct);
        return Response.ok(existingProduct).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(product);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Implementeer andere methoden zoals GET by ID, UPDATE, DELETE indien nodig
}

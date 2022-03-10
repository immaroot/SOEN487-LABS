package ca.concordia.soen487.lab7.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("library")
public class BookRest {

    private static final List<Book> bookList = new ArrayList<>();

    @GET
    @Produces("application/xml")
    public List<Book> getBookList() {
        return bookList;
    }


    @GET
    @Produces("application/xml")
    @Path("{title}")
    public Book getBookDetails(@PathParam("title") String title) {
        title = URLDecoder.decode(title, StandardCharsets.UTF_8);
        String finalTitle = title;
        return bookList.stream()
                .filter(book1 -> Objects.equals(book1.getTitle(), finalTitle))
                .findFirst()
                .orElse(null);
    }


    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response createBook(@HeaderParam("x-api-key") String token,
                               @FormParam("title") String title,
                               @FormParam("author") String author,
                               @FormParam("isbn") String isbn) {
        if (validateToken(token)) {
            bookList.add(new Book(title, author, isbn));
            return Response.status(Response.Status.OK).entity("Success: Created Book.").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Error: User not authenticated.").build();
    }

    @PUT
    @Produces("application/xml")
    public Response updateBook(@HeaderParam("x-api-key") String token,
                           @FormParam("isbn") String isbn,
                           @FormParam("title") String title,
                           @FormParam("author") String author) {
        if (validateToken(token)) {
            Book oldBook = bookList.stream()
                    .filter(book1 -> book1.getIsbn().equals(isbn))
                    .findFirst()
                    .orElse(null);
            if (oldBook != null) {
                oldBook.setTitle(title);
                oldBook.setAuthor(author);
                return Response.status(Response.Status.OK).entity("Success: Book has been updated.").build();
            } else {
                return Response.status(Response.Status.OK).entity("Error: Book does not exists.").build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Error: User not authenticated.").build();
    }

    private boolean validateToken(String token) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("https:/localhost:8443/user/auth");
            httpPost.addHeader("x-api-key", token);
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String isAuthenticated = EntityUtils.toString(entity);
            response.close();
            if (isAuthenticated.equals("true")) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

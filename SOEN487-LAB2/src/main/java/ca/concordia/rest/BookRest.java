package ca.concordia.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("library")
public class BookRest {

    private static final List<Book> bookList = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getBookList() {
        return bookList.stream().map(Object::toString).collect(Collectors.joining(",\n"));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{title}")
    public String getBookDetails(@PathParam("title") String title) {
        try {
            title = URLDecoder.decode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String finalTitle = title;
        Book book = bookList.stream()
                .filter(book1 -> Objects.equals(book1.getTitle(), finalTitle))
                .findFirst()
                .orElse(null);

        if (book != null) {
            return book.toString();
        } else {
            return "no book!";
        }
    }

    @PUT
    @Path("{title}/{isbn}/{author}")
    public void updateBook(@PathParam("title") String title, @PathParam("isbn") String isbn, @PathParam("author") String author) {
        try {
            title = URLDecoder.decode(title, "UTF-8");
            isbn = URLDecoder.decode(isbn, "UTF-8");
            author = URLDecoder.decode(author, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String finalIsbn = isbn;
        Book book = bookList.stream().filter(book1 -> book1.getIsbn().equals(finalIsbn)).findFirst().orElse(null);

        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
        } else {
            bookList.add(new Book(title, author, isbn));
        }
    }
}

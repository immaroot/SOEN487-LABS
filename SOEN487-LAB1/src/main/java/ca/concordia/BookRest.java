package ca.concordia;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
        Book book = bookList.stream()
                .filter(book1 -> Objects.equals(book1.getTitle(), title))
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
        Book book = bookList.stream().filter(book1 -> book1.getIsbn().equals(isbn)).findFirst().orElse(null);

        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
        } else {
            bookList.add(new Book(title, author, isbn));
        }
    }
}

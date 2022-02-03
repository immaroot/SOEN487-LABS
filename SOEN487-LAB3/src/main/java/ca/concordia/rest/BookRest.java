package ca.concordia.rest;

import jakarta.ws.rs.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
        try {
            title = URLDecoder.decode(title, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String finalTitle = title;
        return bookList.stream()
                .filter(book1 -> Objects.equals(book1.getTitle(), finalTitle))
                .findFirst()
                .orElse(null);
    }


    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void createBook(@FormParam("title") String title,
                           @FormParam("author") String author,
                           @FormParam("isbn") String isbn) {
        bookList.add(new Book(title, author, isbn));
    }

    @PUT
    @Consumes("application/xml")
    public void updateBook(Book book) {
        Book oldBook = bookList.stream().filter(book1 -> book1.getIsbn().equals(book.getIsbn())).findFirst().orElse(null);

        if (oldBook != null) {
            oldBook.setTitle(book.getTitle());
            oldBook.setAuthor(book.getAuthor());
        } else {
            bookList.add(new Book(book));
        }
    }
}

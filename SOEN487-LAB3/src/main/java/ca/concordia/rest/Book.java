package ca.concordia.rest;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlElement
    private String title;

    @XmlElement
    private String author;

    @XmlElement
    private String isbn;

    public Book() {
    }

    public Book(String title, String author, String isbn) {
        this.title  = title;
        this.author = author;
        this.isbn   = isbn;
    }

    public Book(Book book) {
        this.title  = book.getTitle();
        this.author = book.getAuthor();
        this.isbn   = book.getIsbn();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor()) && getIsbn().equals(book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getIsbn());
    }
}

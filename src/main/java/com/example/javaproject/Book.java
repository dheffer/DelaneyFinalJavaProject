package com.example.javaproject;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a book entity in the system.
 * It is mapped to the "titles" table in the database.
 * Each book has an ISBN, title, edition number, copyright, and a list of authors.
 */
@Entity(name = "titles")
public class Book {
    // The ISBN of the book. It is the primary key in the "titles" table.
    @Id
    private String isbn;

    // The title of the book.
    private String title;

    // The edition number of the book.
    private int editionNumber;

    // The copyright of the book.
    private String copyright;

    // The list of authors of the book. It is a many-to-many relationship with the "authors" table.
    @ManyToMany
    @JoinTable(
            name = "author_isbn",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Author> authorList = new ArrayList<>();

    /**
     * Sets the ISBN of the book.
     * @param isbn The ISBN of the book.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Sets the title of the book.
     * @param title The title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the edition number of the book.
     * @param editionNumber The edition number of the book.
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Sets the copyright of the book.
     * @param copyright The copyright of the book.
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Returns the ISBN of the book.
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the title of the book.
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the edition number of the book.
     * @return The edition number of the book.
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Returns the copyright of the book.
     * @return The copyright of the book.
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Returns the list of authors of the book.
     * @return The list of authors of the book.
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Sets the list of authors of the book.
     * @param authorList The list of authors of the book.
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
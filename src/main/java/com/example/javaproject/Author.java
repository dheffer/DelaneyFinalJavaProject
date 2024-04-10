package com.example.javaproject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * This class represents an author entity in the system.
 * It is mapped to the "authors" table in the database.
 * Each author has an ID, first name, last name, and a list of books they've authored.
 */
@Entity(name="authors")
public class Author {

    // The ID of the author. It is the primary key in the "authors" table.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // The first name of the author.
    private String firstName;

    // The last name of the author.
    private String lastName;

    // The list of books authored by this author. It is a many-to-many relationship with the "titles" table.
    @ManyToMany(mappedBy = "authorList")
    // @JsonBackReference
    @JsonIgnore
    private List<Book> bookList;

    /**
     * Returns the ID of the author.
     * @return The ID of the author.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the author.
     * @param id The ID of the author.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first name of the author.
     * @return The first name of the author.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the author.
     * @param firstName The first name of the author.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the author.
     * @return The last name of the author.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the author.
     * @param lastName The last name of the author.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the list of books authored by this author.
     * @return The list of books authored by this author.
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Sets the list of books authored by this author.
     * @param bookList The list of books authored by this author.
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
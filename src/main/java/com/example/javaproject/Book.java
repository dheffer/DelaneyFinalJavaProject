package com.example.javaproject;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "titles")
public class Book {
    @Id
    private String isbn;
    private String title;

    private int edition_number;
    private String copyright;
    @ManyToMany
    @JoinTable(
            name = "author_isbn",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )

    private List<Author> authorList = new ArrayList<>();

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEditionNumber(int edition_number) {
        this.edition_number = edition_number;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Get the ISBN
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getEditionNumber() {
        return edition_number;
    }

    public String getCopyright() {
        return copyright;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
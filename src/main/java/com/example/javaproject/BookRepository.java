package com.example.javaproject;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * This interface represents a repository for the Book entity.
 * It extends the CrudRepository interface from Spring Data JPA, which provides methods for CRUD operations.
 * It also declares two additional query methods: findBookByIsbn and findBooksByAuthorListContaining.
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    /**
     * This method is used to find a book by its ISBN.
     * @param isbn The ISBN of the book to find.
     * @return The book with the given ISBN, or null if no such book exists.
     */
    Book findBookByIsbn(String isbn);

    /**
     * This method is used to find all books that contain a given author in their author list.
     * @param author The author to search for in the author lists of the books.
     * @return A list of books that contain the given author in their author list.
     */
    List<Book> findBooksByAuthorListContaining(Author author);
}
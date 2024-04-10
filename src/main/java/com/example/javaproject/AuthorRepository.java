package com.example.javaproject;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * This interface represents a repository for the Author entity.
 * It extends the CrudRepository interface from Spring Data JPA, which provides methods for CRUD operations.
 * It also declares an additional query method: findAuthorByLastName.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    /**
     * This method is used to find an author by their last name.
     * @param lastname The last name of the author to find.
     * @return A list of authors with the given last name.
     */
    List<Author> findAuthorByLastName(String lastname);
}
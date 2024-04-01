package com.example.javaproject;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    List<Author> findAuthorByLastName(String lastname);
}

package com.example.javaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the main controller of the application.
 * It handles all the HTTP requests and responses.
 * It uses the BookRepository and AuthorRepository to perform CRUD operations on the Book and Author entities.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/v1")
public class MainController {

    public static final String BOOK = "/books";
    public static final String AUTHORS = "/authors";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * This method is used to get all the books.
     * @return A list of all the books.
     */
    @GetMapping(path = "/books")
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * This method is used to get a book by its ISBN.
     * @param isbn The ISBN of the book to get.
     * @return The book with the given ISBN.
     */
    @GetMapping(path = BOOK+"/{isbn}")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn){
        return bookRepository.findBookByIsbn(isbn);
    }

    /**
     * This method is used to add a new book.
     * @param isbn The ISBN of the book to add.
     * @param title The title of the book to add.
     * @param edition_number The edition number of the book to add.
     * @param copyright The copyright of the book to add.
     * @param author_id The ID of the author of the book to add.
     * @return A string indicating whether the book was added successfully or not.
     */
    @PostMapping(path = BOOK+"/add")
    public @ResponseBody
    String addNewBook(@RequestParam String isbn, @RequestParam String title, @RequestParam int edition_number, @RequestParam String copyright, @RequestParam Integer author_id){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditionNumber(edition_number);
        book.setCopyright(copyright);
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            book.getAuthorList().add(author.get());
            bookRepository.save(book);
            return "Saved";
        }
        return "Author not found";
    }

    /**
     * This method is used to update a book.
     * @param isbn The ISBN of the book to update.
     * @param title The new title of the book.
     * @param edition_number The new edition number of the book.
     * @param copyright The new copyright of the book.
     * @return A string indicating whether the book was updated successfully or not.
     */
    @PutMapping(path = BOOK+"/update")
    public @ResponseBody
    String updateBook(@RequestParam String isbn, @RequestParam String title, @RequestParam int edition_number, @RequestParam String copyright){
        Book book = bookRepository.findBookByIsbn(isbn);
        if(book != null){
            book.setTitle(title);
            book.setEditionNumber(edition_number);
            book.setCopyright(copyright);
            bookRepository.save(book);
            return "Updated";
        }
        return "Book not found";
    }

    /**
     * This method is used to delete a book.
     * @param isbn The ISBN of the book to delete.
     * @return A string indicating whether the book was deleted successfully or not.
     */
    @DeleteMapping(path = BOOK+"/delete")
    public @ResponseBody
    String deleteBook(@RequestParam String isbn){
        Book book = bookRepository.findBookByIsbn(isbn);
        if(book != null){
            bookRepository.deleteById(Integer.valueOf(isbn));
            return "Deleted";
        }
        return "Book not found";
    }

    /**
     * This method is used to get all the authors.
     * @return A list of all the authors.
     */
    @GetMapping(path = AUTHORS)
    public @ResponseBody
    Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    /**
     * This method is used to get an author by their ID.
     * @param authorId The ID of the author to get.
     * @return The author with the given ID.
     */
    @GetMapping(path = AUTHORS+"/{authorId}")
    public @ResponseBody
    Optional<Author> getAuthorWithId(@PathVariable Integer authorId){
        return authorRepository.findById(authorId);
    }

    /**
     * This method is used to add a new author.
     * @param first The first name of the author to add.
     * @param last The last name of the author to add.
     * @return A string indicating whether the author was added successfully or not.
     */
    @PostMapping(path = AUTHORS+"/add")
    public @ResponseBody
    String addNewAuthor(@RequestParam String first, @RequestParam String last){
        Author author = new Author();
        author.setFirstName(first);
        author.setLastName(last);
        authorRepository.save(author);
        return "Saved";
    }

    /**
     * This method is used to update an author.
     * @param author_id The ID of the author to update.
     * @param first The new first name of the author.
     * @param last The new last name of the author.
     * @return A string indicating whether the author was updated successfully or not.
     */
    @PutMapping(path = AUTHORS + "/update")
    public @ResponseBody
    String updateAuthor(@RequestParam Integer author_id, @RequestParam String first, @RequestParam String last){
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            Author author1 = author.get();
            author1.setFirstName(first);
            author1.setLastName(last);
            authorRepository.save(author1);
            return "Updated";
        }
        return "Author not found";
    }

    /**
     * This method is used to delete an author.
     * @param author_id The ID of the author to delete.
     * @return A string indicating whether the author was deleted successfully or not.
     */
    @DeleteMapping(path = AUTHORS + "/delete")
    public @ResponseBody
    String deleteAuthor(@RequestParam Integer author_id){
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            List<Book> bookList = bookRepository.findBooksByAuthorListContaining(author.get());
            bookList.forEach(book -> {
                book.getAuthorList().remove(author.get());
                bookRepository.save(book);
            });
            authorRepository.delete(author.get());
            return "Deleted";
        }
        return "Author not found";
    }
}
package com.example.javaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/v1")
public class MainController {

    public static final String BOOK = "/books";

    public static final String AUTHORS = "/authors";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path = "/books")
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping(path = BOOK+"/{isbn}")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn){
        return bookRepository.findBookByIsbn(isbn);
    }

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

    @GetMapping(path = AUTHORS)
    public @ResponseBody
    Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping(path = AUTHORS+"/{author_id}")
    public @ResponseBody
    Optional<Author> getAuthorWithId(@PathVariable Integer author_id){
        return authorRepository.findById(author_id);
    }

    @PostMapping(path = AUTHORS+"/add")
    public @ResponseBody
    String addNewAuthor(@RequestParam String first, @RequestParam String last){
        Author author = new Author();
        author.setFirstName(first);
        author.setLastName(last);
        authorRepository.save(author);
        return "Saved";
    }

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

package com.project.Digital_library_System;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {


    private List<Book> bookList;
    private int id;


    // Constructor to initialize the book list and starting ID
    public BookController() {
        this.bookList = new ArrayList<>();
        this.id = 1;

    }


    /**
     * Add a new book to the library.
     * Validates book details before adding.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty() ||
                book.getAuthor() == null || book.getAuthor().isEmpty() ||
                (!book.getAvailabilityStatus().equalsIgnoreCase("Available") && !book.getAvailabilityStatus().equalsIgnoreCase("Checked Out"))) {
            return new ResponseEntity<>("Invalid book details", HttpStatus.BAD_REQUEST);
        }
        book.setId(id++);
        bookList.add(book);
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    /**
     * View all books available in the library.
     */
    @GetMapping("/view-all")
    public ResponseEntity<List<Book>> viewAllBooks() {
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    /**
     * Search for a book by ID or Title.
     * Returns the book if found, otherwise returns a 404 error.
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam(required = false) Integer id, @RequestParam(required = false) String title) {
        if (id != null) {
            for (Book book : bookList) {
                if (book.getId() == id) {
                    return new ResponseEntity<>(book, HttpStatus.OK);
                }
            }
        } else if (title != null) {
            List<Book> filteredBooks = bookList.stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
                    .collect(Collectors.toList());
            if (!filteredBooks.isEmpty()) {
                return new ResponseEntity<>(filteredBooks, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Update details of an existing book using its ID.
     * Supports updating title, author, and availability status.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                if (updatedBook.getTitle() != null && !updatedBook.getTitle().isEmpty()) {
                    book.setTitle(updatedBook.getTitle());
                }
                if (updatedBook.getAuthor() != null && !updatedBook.getAuthor().isEmpty()) {
                    book.setAuthor(updatedBook.getAuthor());
                }
                if (updatedBook.getAvailabilityStatus() != null && (updatedBook.getAvailabilityStatus().equalsIgnoreCase("Available") || updatedBook.getAvailabilityStatus().equalsIgnoreCase("Checked Out"))) {
                    book.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
                }
                return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a book using its ID.
     * Returns success message if deleted, otherwise returns a 404 error.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        boolean removed = bookList.removeIf(book -> book.getId() == id);
        if (removed) {
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }
    /**
     * Exit the application gracefully.
     */
    @PostMapping("/exit")
    public ResponseEntity<String> exitApplication() {
        System.out.println("Exiting the system. Goodbye!");
        System.exit(0);
        return new ResponseEntity<>("System is shutting down.", HttpStatus.OK);
    }

    }


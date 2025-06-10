  package com.bezkoder.springjwt.controllers;

  import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.data.domain.Page;
  import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.PostMapping;
  import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RestController;

  import com.bezkoder.springjwt.models.Book;
  import com.bezkoder.springjwt.models.User;
  import com.bezkoder.Service.*; 
  import com.bezkoder.*;
  
  @RestController
  @RequestMapping("/api/book")
  public class BookController {
      @Autowired
      private BookService bookService;

      @GetMapping("/all")
      public Page<Book> getAll(Pageable pageable) {
        return  bookService.getAllBooks(pageable);
        
      }

      @GetMapping("/{id}/related")
      public List<Book> books(@PathVariable("id") int id){
      return bookService.relatedBooks(id);
      }


      @GetMapping("/{id}")
      public Book getAll(@PathVariable("id") int id) {
        return  bookService.getBook(id);
        
      }


      @PutMapping("/{id}")
      public Book putBook(@PathVariable("id") int id,Book reqBook) {
        return  bookService.putBook(id, reqBook);
        
      }
      @PostMapping("/post")
      public Book postbook( @RequestBody Book reqBook) {
        return  bookService.postBook(reqBook);
        
      }
      @DeleteMapping("/{id}")
      public String deleteuser(@PathVariable("id")int id){
          return bookService.deleteBook(id);
      }
      
  }

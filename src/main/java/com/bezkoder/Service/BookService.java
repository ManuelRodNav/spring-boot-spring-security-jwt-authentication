package com.bezkoder.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Book;
import com.bezkoder.springjwt.repository.BookRepository;


@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
public Page<Book> getAllBooks(Pageable pageable) {
    Page<Book> page = bookRepository.findAll(pageable);
    LocalDate nowDate = LocalDate.now();

    List<Book> books = page.getContent();

    for (Book book : books) {
        Date publicationDate = book.getPublicationdate();

        if (publicationDate != null) {
            LocalDate pubLocalDate = publicationDate.toInstant()
                                                     .atZone(ZoneId.systemDefault())
                                                     .toLocalDate();

            if (nowDate.minusDays(2).isBefore(pubLocalDate)) {
                // Solo aplicas el precio con descuento como un campo nuevo o modificable, pero NO alteras el original
                double originalPrice = book.getPrice();
                double discount = book.getDisccount(); // asumes que es porcentaje, ej. 10 para 10%
                double discountedPrice = originalPrice * (1 - discount / 100.0);

                
                book.setPrice(discountedPrice); 
            }
        }
    }

    return page;
}

    public Book getBook(Integer idInteger){
        Book book= bookRepository.findById(idInteger).orElseThrow();
        return book;
    }
    public Book postBook(Book reqBook){
        int[] ratinglist = reqBook.getListrating();
int ratetotal = 0;

for (int i = 0; i < ratinglist.length; i++) {
    ratetotal += ratinglist[i]; // aquí estaba el error
}

int promedio = 0;
if (ratinglist.length > 0) {
    promedio = (int) Math.floor(ratetotal / ratinglist.length);
}

        Book postedbook= new Book();
        postedbook.setCategories(reqBook.getCategories());
        postedbook.setImage(reqBook.getImage());
        postedbook.setListrating(reqBook.getListrating());
        postedbook.setPublicationdate(new Date());
        postedbook.setRating(
           promedio
        );
        postedbook.setSinopsis(reqBook.getSinopsis());
        postedbook.setTitle(reqBook.getTitle());
        bookRepository.save(postedbook);
        return postedbook;
    }
    public Book putBook(int id,Book reqBook){
        Book putBook= bookRepository.findById(id).orElseThrow();
        putBook.setCategories(reqBook.getCategories());
        putBook.setImage(reqBook.getImage());
        putBook.setListrating(reqBook.getListrating());
        putBook.setPublicationdate(reqBook.getPublicationdate());
        putBook.setRating(reqBook.getRating());
        putBook.setSinopsis(reqBook.getSinopsis());
        putBook.setTitle(reqBook.getTitle());
        bookRepository.save(putBook);
        return  putBook;
    }

    public String deleteBook(int id){
        Map<String,String> mapped= new HashMap<>();
        Book bookfound= bookRepository.findById(id).orElseThrow();
        if(bookfound==null){
            return mapped.put("Accion denegada", "no se ha podido completar la accion");
        }
        bookRepository.delete(bookfound);
        return mapped.put("Accion realizada","se ha realizado la accion correctamente");
    }
    
    public List<Book> dodisscountmanually(List<Integer> idbooks,int descuento){
        List<Book> books= bookRepository.findAll();
        List<Book> libroscondescuento= new ArrayList<>();

for (Book libro : books) {
    if (idbooks.contains(libro.getId())) {
        // Coincide: aplicar descuento
        libro.setPrice(libro.getPrice() * (descuento/100)); //descuento manual
        libroscondescuento.add(libro);
    }
    }
    return libroscondescuento;
}
public List<Book> relatedBooks(int id) {
    List<Book> allBooks = bookRepository.findAll();
    Book mainBook = bookRepository.findById(id).orElseThrow();

    Set<String> mainCategories = new HashSet<>(Arrays.asList(mainBook.getCategories()));
    List<Book> relatedBooks = new ArrayList<>();

    for (Book candidate : allBooks) {
        if (candidate.getId().equals(id)) continue;

        for (String category : candidate.getCategories()) {
            if (mainCategories.contains(category)) {
                relatedBooks.add(candidate);
                break;
            }
        }
    }
    //en el frontend haz que devuelva solo el numero que tu quieras ya que esto devuelve la lista de todos 
    
    return relatedBooks;
}




}
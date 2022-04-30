package saha.codes.sdjpa_jdbc_template_practice.dao;

import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BookDao {


    List<Book> findAllBooksSortByTitle(Pageable pageable);
    List<Book> findAllBooks(Pageable pageable);
    List<Book> findAllBooks(int pageSize, int offset);// offset must not exceed number of rows in DB
    List<Book> findAllBooks();
    Book getById(Long id);

    Book findByTitle(String title);

    Book saveBook(Book newBook);

    Book updateBook(Book savedBook);

    Integer getBookCount();

    void deleteById(Long id);
}

package saha.codes.sdjpa_jdbc_template_practice.dao;

import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

public interface BookDao {

    Book getById(Long id);

    Book findByTitle(String title);

    Book saveBook(Book newBook);

    Book updateBook(Book savedBook);

    Integer getBookCount();

    void deleteById(Long id);
}

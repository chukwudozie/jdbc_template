package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.stereotype.Component;
import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

@Component
public class BookDaoImpl implements BookDao {

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        return null;
    }

    @Override
    public Book saveBook(Book newBook) {
        return null;
    }

    @Override
    public Book updateBook(Book savedBook) {
        return null;
    }

    @Override
    public int getBookCount() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }
}

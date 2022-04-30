package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@Component(value = "bookDaoImpl")
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate template;
    public BookDaoImpl(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return new ArrayList<>(); // this method is fully implemented in BookDaoJDBCTemplate
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return new ArrayList<>(); // this method is fully implemented in BookDaoJDBCTemplate
    }

    @Override
    public List<Book> findAllBooks() {
        return new ArrayList<>(); // this method is fully implemented in BookDaoJDBCTemplate
    }

    @Override
    public Book getById(Long id) {
        return template.queryForObject("SELECT * FROM book WHERE id = ?", getBookMapper(),id);
    }

    @Override
    public Book findByTitle(String title) {
        return template.queryForObject("SELECT * FROM book WHERE title = ?", getBookMapper(),title);
    }

    @Override
    public Book saveBook(Book newBook) {
        template.update("INSERT INTO book (isbn, publisher, title, author_id) VALUES (?,?,?,?)",
                newBook.getIsbn(),newBook.getPublisher(), newBook.getTitle(), newBook.getAuthorId());
        Long newBookId = template.queryForObject("SELECT LAST_INSERT_ID()",Long.class);
        return this.getById(newBookId);
    }

    @Override
    public Book updateBook(Book book) {
        template.update("UPDATE book SET isbn = ?, publisher =? , title =?, author_id =? WHERE id = ?",
           book.getIsbn(),book.getPublisher(),book.getTitle(),book.getAuthorId(),book.getId());
        Long bookId = template.queryForObject("SELECT LAST_INSERT_ID()",Long.class);
        return this.getById(bookId);

    }

    @Override
    public Integer getBookCount() {
        return template.queryForObject("SELECT COUNT(*) FROM book",Integer.class);
    }

    @Override
    public void deleteById(Long id) {
        template.update("DELETE FROM book WHERE id = ?",id);
    }

    private RowMapper<Book> getBookMapper(){
        return new BookMapper();
    }
}

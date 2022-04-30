package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

import java.util.List;

@Component(value = "bookDaoJDBCTemplate")
public class BookDaoJDBCTemplate implements BookDao{

    private final JdbcTemplate template;

    public BookDaoJDBCTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     *
     * @param pageSize the number of books in the one page
     * @param offset the number of rows to be skipped
     * @return List of books with size pageSize
     */
    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return template.query("SELECT * FROM book limit ? offset ?",getBookMapper(),pageSize,offset);
    }

    @Override
    public List<Book> findAllBooks() {
        return template.query("SELECT * FROM book",getBookMapper());
    }

    @Override
    public Book getById(Long id) {
        return template.queryForObject("SELECT * FROM book WHERE id = ?", getBookMapper(),id);
    }

    @Override
    public Book findByTitle(String title) {
        return template.queryForObject("SELECT * FROM book WHERE title = ?",getBookMapper(),title);
    }

    @Override
    public Book saveBook(Book newBook) {
         template.update("INSERT INTO book (isbn, publisher, title, author_id) VALUES " +
                "(?, ?, ?, ?)",newBook.getIsbn(),newBook.getPublisher(),newBook.getTitle(),newBook.getAuthorId());
         Long savedId = template.queryForObject("SELECT LAST_INSERT_ID()",Long.class);
         return this.getById(savedId);
    }

    @Override
    public Book updateBook(Book savedBook) {
         template.update("UPDATE book SET isbn = ?, publisher = ?, title = ?, author_id = ? " +
                "WHERE id = ?",savedBook.getIsbn(),savedBook.getPublisher(), savedBook.getTitle(),
                savedBook.getAuthorId(), savedBook.getId());
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

    private BookMapper getBookMapper (){
        return new BookMapper();
    }
}

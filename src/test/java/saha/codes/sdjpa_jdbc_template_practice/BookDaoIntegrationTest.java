package saha.codes.sdjpa_jdbc_template_practice;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import saha.codes.sdjpa_jdbc_template_practice.dao.AuthorDao;
import saha.codes.sdjpa_jdbc_template_practice.dao.BookDao;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;
import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackages = {"saha.codes.sdjpa_jdbc_template_practice.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testGetBookById(){
        Book book = bookDao.getById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    public void testFindBookByTitle(){
        Book book = bookDao.findByTitle("First Book");
        assertThat(book).isNotNull();
    }

    @Test
    public void testSaveBook(){
        Book newBook = new Book("test book","2468","test author");
        Author author = new Author("first name", "last name");
        Author savedAuthor = authorDao.save(author);
        newBook.setAuthorId(savedAuthor.getId());
        Book saved = bookDao.saveBook(newBook);
        assertThat(saved).isNotNull();
    }

    @Test
    public void testUpdateBook(){
        Book book = new Book("update title","test isbn", "test author");
        Author author = new Author("first name", "last name");
        Author savedAuthor = authorDao.save(author);
        book.setAuthorId(savedAuthor.getId());
        Book savedBook = bookDao.saveBook(book);
        savedBook.setIsbn("updated test isbn");
        Book updatedBook = bookDao.updateBook(savedBook);
        assertThat(updatedBook.getIsbn()).isEqualTo("updated test isbn");

    }


    @Test
    public void testBookCount(){
        int count = bookDao.getBookCount();
        assertThat(count).isGreaterThan(0);
    }

    @Test
    public void testDeleteBookById(){
          Book testBook = new Book("test title", "123", "test publisher");
       Book savedBook =bookDao.saveBook(testBook);
       bookDao.deleteById(savedBook.getId());
       Book fetchBook = bookDao.getById(savedBook.getId());
       assertThat(fetchBook).isNull();

    }

}

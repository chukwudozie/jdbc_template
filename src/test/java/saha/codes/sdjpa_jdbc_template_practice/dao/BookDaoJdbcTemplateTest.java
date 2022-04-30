package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;
import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = {"saha.codes.sdjpa_jdbc_template_practice.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoJdbcTemplateTest {

    @Autowired
    @Qualifier(value = "bookDaoJDBCTemplate")
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;

    @Test
    void testFindAllBooksPage1(){
        List<Book>firstTenBooks = bookDao.findAllBooks(10,0);
        firstTenBooks.forEach(book -> {
            System.out.printf("Title : %s   Publisher: %s  ID : %d \n",
                    book.getTitle(),book.getPublisher(),book.getId());
        });
        assertThat(firstTenBooks).isNotNull();
        assertThat(firstTenBooks.size()).isEqualTo(10);
    }
    @Test
    void testFindAllBooksPage2(){
        List<Book>secondTenBooks = bookDao.findAllBooks(10,10);
        assertThat(secondTenBooks).isNotNull();
        assertThat(secondTenBooks.size()).isEqualTo(10);
    }
    @Test
    void testFindAllBooksPage3(){
        List<Book>thirdTenBooks = bookDao.findAllBooks(10,20);
        assertThat(thirdTenBooks).isNotNull();
        assertThat(thirdTenBooks.size()).isEqualTo(0);
    }

    @Test
    void testFindAllBooksPage1_pageable(){
        List<Book>firstTenBooks = bookDao.findAllBooks(PageRequest.of(0,10));
        assertThat(firstTenBooks).isNotNull();
        assertThat(firstTenBooks.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage1_sortByTitle(){
        List<Book>sortedBooks = bookDao.findAllBooksSortByTitle(PageRequest.of(0,10,
                Sort.by(Sort.Order.desc("title"))));
        sortedBooks.forEach(book -> {
            System.out.printf("Title : %s   Publisher: %s  ID : %d \n",
                    book.getTitle(),book.getPublisher(),book.getId());
        });
        assertThat(sortedBooks).isNotNull();
        assertThat(sortedBooks.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooks(){
        List<Book> books = bookDao.findAllBooks();
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(4);
    }

    @Test
    void testGetById(){
        Book book = bookDao.getById(3L);
        assertThat(book).isNotNull();
    }
    @Test
    public void testFindBookByTitle(){
        Book book = bookDao.findByTitle("Clean Code");
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
        System.out.println(count);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    public void testDeleteBookById(){
        Book testBook = new Book("test title", "123", "test publisher");
        Book savedBook =bookDao.saveBook(testBook);
        bookDao.deleteById(savedBook.getId());
        assertThrows(EmptyResultDataAccessException.class,() -> {
            bookDao.getById(savedBook.getId());
        });
    }

}

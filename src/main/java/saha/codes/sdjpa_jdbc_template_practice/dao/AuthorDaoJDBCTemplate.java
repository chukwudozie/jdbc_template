package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.stereotype.Component;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;

@Component(value = "authorDaoJDBC")
public class AuthorDaoJDBCTemplate implements AuthorDao{

    @Override
    public Author save(Author author) {
        return new Author(); // method already implemented
    }

    @Override
    public Author getById(Long id) {
        return new Author();
    }
}

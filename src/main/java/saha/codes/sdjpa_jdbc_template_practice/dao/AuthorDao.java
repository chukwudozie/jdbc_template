package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.data.domain.Pageable;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;


import java.util.List;

public interface AuthorDao {

//    List<Author> findAllAuthorsByLastName();
    List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable);
    Author save(Author author);
    Author getById(Long id);

}

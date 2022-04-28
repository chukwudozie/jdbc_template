package saha.codes.sdjpa_jdbc_template_practice.dao;

import saha.codes.sdjpa_jdbc_template_practice.domain.Author;

public interface AuthorDao {

    Author save(Author author);
    Author getById(Long id);
}

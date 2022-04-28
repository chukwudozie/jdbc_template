package saha.codes.sdjpa_jdbc_template_practice.dao;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorExtractor implements ResultSetExtractor<Author> {
    @Override
    public Author extractData(ResultSet rs) throws SQLException, DataAccessException {
        return new AuthorMapper().mapRow(rs,0);
    }
}

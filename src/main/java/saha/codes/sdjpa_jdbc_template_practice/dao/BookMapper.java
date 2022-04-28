package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.jdbc.core.RowMapper;
import saha.codes.sdjpa_jdbc_template_practice.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getLong("id"),rs.getString("title"),
                rs.getString("isbn"), rs.getString("publisher"));
    }
}

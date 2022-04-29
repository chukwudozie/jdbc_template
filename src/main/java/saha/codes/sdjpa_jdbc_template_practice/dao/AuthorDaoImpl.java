package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate template;

    public AuthorDaoImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Author getById(Long id) {
        String sql = "select author.id as id, first_name, last_name, book.id as book_id, book.isbn, book.publisher," +
          " book.title from author left outer join book on author.id = book.author_id where author.id = ?";
        return template.query(sql, new AuthorExtractor(),id);
    }

    @Override
    public Author save(Author author) {
        template.update("INSERT INTO author (first_name, last_name) VALUES (?, ?)",
                author.getFirstName(), author.getLastName());
        Long savedId = template.queryForObject("SELECT LAST_INSERT_ID()",Long.class);
        return this.getById(savedId);
    }

    private RowMapper<Author> getRowMapper(){
        return new AuthorMapper();
    }
}

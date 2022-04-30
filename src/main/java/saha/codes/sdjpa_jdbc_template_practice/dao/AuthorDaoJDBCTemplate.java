package saha.codes.sdjpa_jdbc_template_practice.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;
import java.util.List;

@Component(value = "authorDaoJDBC")
public class AuthorDaoJDBCTemplate implements AuthorDao{
    private final JdbcTemplate template;

    public AuthorDaoJDBCTemplate(JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM author WHERE last_name = ? ");
        if(pageable.getSort().getOrderFor("firstname") != null){
            sql.append("order by first_name ").append(pageable.getSort()
                    .getOrderFor("firstname").getDirection().name());
        }
        sql.append(" limit ? offset ?");
        return template.query(sql.toString(),getRowMapper(),lastName,pageable.getPageSize(),pageable.getOffset());
    }

    @Override
    public Author save(Author author) {
        return new Author(); // method already implemented
    }

    @Override
    public Author getById(Long id) {
        return new Author();
    }
    private RowMapper<Author> getRowMapper(){
        return new AuthorMapperJDBC();
    }
}

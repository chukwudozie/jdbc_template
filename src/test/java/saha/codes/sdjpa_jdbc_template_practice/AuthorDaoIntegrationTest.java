package saha.codes.sdjpa_jdbc_template_practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import saha.codes.sdjpa_jdbc_template_practice.dao.AuthorDao;
import saha.codes.sdjpa_jdbc_template_practice.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ComponentScan(basePackages = {"saha.codes.sdjpa_jdbc_template_practice.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

//    @Test
//    void testGetAuthorByName() {
//        Author author = authorDao.findAuthorByName("Craig", "Walls");
//
//        assertThat(author).isNotNull();
//    }

    @Test
    void testGetAuthor() {
        Author author = authorDao.getById(2L);
        assertThat(author.getId()).isNotNull();
    }
}

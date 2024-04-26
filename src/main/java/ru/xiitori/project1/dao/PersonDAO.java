package ru.xiitori.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.xiitori.project1.dao.mappers.BookMapper;
import ru.xiitori.project1.dao.mappers.PersonMapper;
import ru.xiitori.project1.models.Book;
import ru.xiitori.project1.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO person(full_name, birth_year) VALUES(?, ?)",
                person.getFullName(), person.getBirthYear());
    }

    public Person get(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE person SET full_name = ?, birth_year = ? WHERE person_id = ?",
                person.getFullName(), person.getBirthYear(), person.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id = ?", id);
    }

    public List<Book> getBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?",
                new Object[]{id}, new BookMapper());
    }
}

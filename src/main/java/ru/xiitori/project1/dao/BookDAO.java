package ru.xiitori.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.xiitori.project1.dao.mappers.BookMapper;
import ru.xiitori.project1.dao.mappers.PersonMapper;
import ru.xiitori.project1.models.Book;
import ru.xiitori.project1.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
    }

    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year = ? WHERE book_id = ?",
                book.getTitle(), book.getAuthor(), book.getYear(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", id);
    }

    public Optional<Person> getOwner(int bookId) {
        return jdbcTemplate.query("SELECT person.* FROM person JOIN book ON person.person_id = book.person_id" +
                        " WHERE book.book_id = ?", new Object[]{bookId}, new PersonMapper()).stream().findAny();
    }

    public void assign(int bookId, int personId) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE book_id = ?",
                personId, bookId);
    }

    public void release(int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id = NULL WHERE book_id = ?", bookId);
    }

    public Optional<Book> getBookByTitle(Book book) {
        return jdbcTemplate.query("SELECT * FROM book WHERE title = ? AND book_id != ?",
                new Object[]{book.getTitle(), book.getId()}, new BookMapper()).stream().findAny();
    }
}

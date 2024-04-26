package ru.xiitori.project1.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.xiitori.project1.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt(1));
        book.setTitle(rs.getString(3));
        book.setAuthor(rs.getString(4));
        book.setYear(rs.getInt(5));

        return book;
    }
}

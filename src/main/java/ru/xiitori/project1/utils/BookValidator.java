package ru.xiitori.project1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.xiitori.project1.dao.BookDAO;
import ru.xiitori.project1.models.Book;

import java.util.Optional;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book validateBook = (Book) target;
        Optional<Book> book = bookDAO.getBookByTitle(validateBook);
        if (book.isPresent()) {
            errors.rejectValue("title", "", "title should be unique");
        }
    }
}

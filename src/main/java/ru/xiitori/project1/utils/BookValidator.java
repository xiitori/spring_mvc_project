package ru.xiitori.project1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.xiitori.project1.models.Book;
import ru.xiitori.project1.services.BooksService;

import java.util.Optional;

@Component
public class BookValidator implements Validator {
    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService service) {
        this.booksService = service;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book validateBook = (Book) target;
        Optional<Book> book = booksService.findByTitle(validateBook.getTitle());
        if (book.isPresent() && book.get().getId() != validateBook.getId()) {
            errors.rejectValue("title", "", "title should be unique");
        }
    }
}

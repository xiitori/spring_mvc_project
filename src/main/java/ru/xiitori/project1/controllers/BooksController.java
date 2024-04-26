package ru.xiitori.project1.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.xiitori.project1.dao.BookDAO;
import ru.xiitori.project1.dao.PersonDAO;
import ru.xiitori.project1.models.Book;
import ru.xiitori.project1.models.Person;
import ru.xiitori.project1.utils.BookValidator;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;

    private final PersonDAO personDAO;

    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.get(id));

        Optional<Person> bookOwner = bookDAO.getOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("bookOwner", bookOwner.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }

        return "/books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.get(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}")
    public String patch(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.update(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int bookId,
                         @ModelAttribute("person") Person person) {
        bookDAO.assign(bookId, person.getId());
        return "redirect:/books";

    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int bookId) {
        bookDAO.release(bookId);
        return "redirect:/books";
    }
}

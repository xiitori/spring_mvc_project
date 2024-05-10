package ru.xiitori.project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xiitori.project1.models.Book;
import ru.xiitori.project1.models.Person;
import ru.xiitori.project1.repositories.BooksRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean isSorted) {
        if (isSorted) {
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }

    public List<Book> findAll(int page, int booksPerPage, boolean isSorted) {
        if (isSorted) {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).toList();
        } else {
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).toList();
        }
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void add(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getOwner(int id) {
        return Optional.ofNullable(booksRepository.findById(id).get().getOwner());
    }

    @Transactional
    public void assign(int bookId, Person person) {
        Optional<Book> optional = booksRepository.findById(bookId);

        if (optional.isEmpty()) {
            return;
        }

        Book book = optional.get();

        book.setStartedAt(new Date());
        book.setOwner(person);
    }

    @Transactional
    public void release(int bookId) {
        Optional<Book> optional = booksRepository.findById(bookId);

        if (optional.isEmpty()) {
            return;
        }

        Book book = optional.get();

        book.setExpired(false);
        book.setStartedAt(null);
        book.setOwner(null);
    }

    public Optional<Book> findByTitle(String title) {
        return booksRepository.findByTitle(title);
    }

    public List<Book> searchBooks(String beginning) {
        return booksRepository.findByTitleStartingWith(beginning);
    }
}

package ru.xiitori.project1.models;

import jakarta.validation.constraints.*;

public class Book {

    private int id;

    @Size(min = 4, max = 200, message = "title size should be between 4 and 200 symbols")
    private String title;

    @Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+ [A-Z][a-z]+$",
            message = "The author's name should be in the format: Surname First name Middle name")
    @Size(min = 5, max = 200, message = "Author name should be between 5 and 200 symbols")
    private String author;

    @Positive(message = "year should be greater than 0")
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

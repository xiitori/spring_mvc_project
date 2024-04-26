package ru.xiitori.project1.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Person {

    private int id;

    @Size(min = 5, max = 200, message = "Full name size should be between 5 and 200 symbols")
    private String fullName;

    @Min(value = 1901, message = "birth year should be greater than 1900")
    private int birthYear;

    public Person(int id, String fullName, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

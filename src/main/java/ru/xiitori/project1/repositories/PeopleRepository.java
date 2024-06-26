package ru.xiitori.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xiitori.project1.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}

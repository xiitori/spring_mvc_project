package ru.xiitori.project1.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.xiitori.project1.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt(1));
        person.setFullName(rs.getString(2));
        person.setBirthYear(rs.getInt(3));

        return person;
    }
}

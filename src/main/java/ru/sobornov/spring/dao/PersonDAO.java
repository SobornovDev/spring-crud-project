package ru.sobornov.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sobornov.spring.exceptions.PersonNotFoundException;
import ru.sobornov.spring.mappers.PersonMapper;
import ru.sobornov.spring.models.Person;

import java.util.List;

/**
 * @author : Sobornov Vladimir
 * @since : 08.10.2022
 **/
@Component
@RequiredArgsConstructor
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    private static final String INDEX_QUERY = "SELECT * FROM Person";
    private static final String SHOW_QUERY = "SELECT * FROM Person WHERE id=?";
    private static final String SAVE_QUERY = "INSERT INTO Person VALUES(1, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Person SET name=?,age=? email=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM Person WHERE id=?";


    public List<Person> index(){
        return jdbcTemplate.query(INDEX_QUERY, new PersonMapper());
    }

    public Person show(int id) throws PersonNotFoundException {
        return jdbcTemplate.query(SHOW_QUERY, new Object[]{id}, new PersonMapper())
                .stream()
                .findAny()
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void save(Person person) {
        jdbcTemplate.update(SAVE_QUERY, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(UPDATE_QUERY, person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
}

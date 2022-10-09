package ru.sobornov.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sobornov.spring.exceptions.PersonNotFoundException;
import ru.sobornov.spring.mappers.PersonMapper;
import ru.sobornov.spring.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sobornov Vladimir
 * @since : 08.10.2022
 **/
@Component
@RequiredArgsConstructor
public class PersonDAO {

    private static final String INDEX_QUERY = "SELECT * FROM Person";
    private static final String SHOW_QUERY = "SELECT * FROM Person WHERE id=?";
    private static final String SAVE_QUERY = "INSERT INTO Person VALUES(1, ?, ?, ?)";
    private static final String INSERT_QUERY = "INSERT INTO Person VALUES(?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Person SET name=?,age=?, email=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM Person WHERE id=?";
    private static final int BATCH_SIZE = 1000;
    private final JdbcTemplate jdbcTemplate;

    public List<Person> index() {
        return jdbcTemplate.query(INDEX_QUERY, new PersonMapper());
    }

    public Person show(int id) throws PersonNotFoundException {
        return jdbcTemplate.query(SHOW_QUERY, new Object[]{id}, new PersonMapper()).stream().findAny().orElseThrow(() -> new PersonNotFoundException(id));
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

    public void multipleUpdate() {
        List<Person> people = create1000people();
        for (Person p : people) {
            jdbcTemplate.update(INSERT_QUERY,p.getId(), p.getName(), p.getAge(), p.getEmail());
        }
    }

    private List<Person> create1000people() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            people.add(new Person(i, "test_name" + i, 30, "test" + i + "@mail.com"));
        }
        return people;
    }

    public void multipleBatchUpdate() {
        List<Person> people = new ArrayList<>();
        jdbcTemplate.batchUpdate(INSERT_QUERY, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, people.get(i).getId());
                preparedStatement.setString(2, people.get(i).getName());
                preparedStatement.setInt(3, people.get(i).getAge());
                preparedStatement.setString(4, people.get(i).getEmail());

            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
    }

}

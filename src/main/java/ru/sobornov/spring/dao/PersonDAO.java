package ru.sobornov.spring.dao;

import org.springframework.stereotype.Component;
import ru.sobornov.spring.exceptions.PersonNotFoundException;
import ru.sobornov.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sobornov Vladimir
 * @since : 08.10.2022
 **/
@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;

    private PersonDAO() {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Tom"));
        people.add(new Person(++PEOPLE_COUNT,"Bob"));
        people.add(new Person(++PEOPLE_COUNT,"John"));
        people.add(new Person(++PEOPLE_COUNT,"Alice"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id) throws PersonNotFoundException {
        return people.stream().filter(p -> p.id() == id).findAny().orElseThrow(() -> new PersonNotFoundException(id));
    }
}

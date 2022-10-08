package ru.sobornov.spring.exceptions;

import ru.sobornov.spring.models.Person;

/**
 * @author : Sobornov Vladimir
 * @since : 08.10.2022
 **/
public class PersonNotFoundException extends Exception {
    private int id;
    public PersonNotFoundException(int id) {
        this.id = id;
    }
    @Override
    public String getMessage() {
        return "Person not found. Id: " + id;
    }
}

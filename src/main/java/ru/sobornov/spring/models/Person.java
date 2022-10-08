package ru.sobornov.spring.models;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : Sobornov Vladimir
 * @since : 08.10.2022
 **/
@AllArgsConstructor
@Data
public class Person {
    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}

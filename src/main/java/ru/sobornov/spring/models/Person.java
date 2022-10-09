package ru.sobornov.spring.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Sobornov Vladimir
 * @since : 08.10.2022
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private int id;
    private String name;

    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package ru.sobornov.spring.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sobornov.spring.dao.PersonDAO;
import ru.sobornov.spring.exceptions.PersonNotFoundException;
import ru.sobornov.spring.models.Person;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Sobornov Vladimir
 * @since : 08.10.2022
 **/
@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
@Slf4j
public class PeopleController  {

    private final PersonDAO personDAO;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        personDAO.index();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("person", personDAO.show(id));
        } catch (PersonNotFoundException e) {
            log.error(e.getMessage());
        }
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person(0,null));
        return "people/new";
    }
    @PostMapping
    public String create(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        Person person = new Person(0, name);
        model.addAttribute("person", person);
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        try {
            model.addAttribute("person", personDAO.show(id));
        } catch (PersonNotFoundException e) {
            log.error(e.getMessage());
        }
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(Model model, HttpServletRequest request, @PathVariable("id") int id) {
        String name = request.getParameter("name");
        Person person = new Person(id, name);
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}

package ru.sobornov.spring.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sobornov.spring.dao.PersonDAO;
import ru.sobornov.spring.exceptions.PersonNotFoundException;

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


}

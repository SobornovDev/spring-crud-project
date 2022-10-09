package ru.sobornov.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sobornov.spring.dao.PersonDAO;

/**
 * @author : Sobornov Vladimir
 * @since : 09.10.2022
 **/

@Controller
@RequestMapping("/batch-update")
@RequiredArgsConstructor
public class BatchController {

    private final PersonDAO personDAO;

    @GetMapping
    public String index() {
        return "batch/index";
    }

    @GetMapping("/without")
    public String withoutBatch() {
        personDAO.multipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String batchUpdate() {
        personDAO.multipleBatchUpdate();
        return "redirect:/people";
    }
}

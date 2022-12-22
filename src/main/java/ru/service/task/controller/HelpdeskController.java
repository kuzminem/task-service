package ru.service.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.service.task.service.HelpdeskService;

import java.security.Principal;

/**
 * Корневая страница.
 */
@Controller
@RequestMapping(value = "/")
public class HelpdeskController {

    private final HelpdeskService helpdeskService;

    @Autowired
    public HelpdeskController(HelpdeskService helpdeskService) {
        this.helpdeskService = helpdeskService;
    }

    @GetMapping
    public String showTasks(@RequestParam(required = false) String v, Model model, Principal principal) {
        long view = (v == null) ? 0 : Long.parseLong(v);
        model.addAttribute("view", view);
        model.addAttribute("tasks", helpdeskService.getTasks(principal.getName(), view));
        return "helpdesk";
    }

    @PostMapping
    public String changeView(@RequestParam String v) {
        long view;
        switch (v) {
            case "Исп. не назначен":
                view = 1;
                break;
            case "В работе":
                view = 2;
                break;
            case "В ожидании":
                view = 3;
                break;
            case "Выполненные":
                view = 4;
                break;
            case "Все":
                view = 5;
                break;
            default:
                view = 0;
                break;
        }
        return "redirect:/?v=" + view;
    }
}

package ru.service.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/help")
public class HelpController {

    @GetMapping
    public String showHelp() {
        return "/help";
    }
}

package ru.service.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.service.task.controller.entity.UserDto;
import ru.service.task.service.UserService;

@Controller
@RequestMapping(value = "/mgmt")
public class MgmtController {

    private final UserService userService;

    @Autowired
    public MgmtController(UserService userService) {
        this.userService = userService;
    }

    /**
     * /mgmt
     */
    @GetMapping
    public String showUsers(Model model) {
        // getAllUsers
        model.addAttribute("users", userService.getAll());
        return "mgmt";
    }

    /**
     * Добавить пользователя.
     * Синяя кнопка "+" на странице /mgmt.
     */
    @PostMapping
    public String newUser(@RequestParam("username") String username,
                          @RequestParam("lastName") String lastName,
                          @RequestParam("firstName") String firstName,
                          @RequestParam("secondName") String secondName,
                          @RequestParam("password") String password,
                          @RequestParam("credentials") String credentials) {
        if (userService.approval(username)) {
            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setLastName(lastName);
            userDto.setFirstName(firstName);
            userDto.setSecondName(secondName);
            userDto.setPassword(password);
            userDto.setCredentials(credentials);
            userService.newUser(userDto);
        }
        return "redirect:/mgmt";
    }
}
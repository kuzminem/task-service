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
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * /user
     */
    @GetMapping
    public String showUser(@RequestParam long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    /**
     * Обновить пользователя.
     * Синяя кнопка "+" на странице /user.
     */
    @PostMapping
    public String updateUser(@RequestParam("userId") long userId,
                             @RequestParam("password") String password,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("secondName") String secondName,
                             @RequestParam("credentials") String credentials) {
        if (!password.equals("")
                && !lastName.equals("")
                && !firstName.equals("")
                && !secondName.equals("")
                && !credentials.equals("")) {
            UserDto userDto = new UserDto();
            userDto.setId(userId);
            userDto.setPassword(password);
            userDto.setCredentials(credentials);
            userDto.setLastName(lastName);
            userDto.setFirstName(firstName);
            userDto.setSecondName(secondName);

            userService.updateUser(userDto);
        }
        return "redirect:/mgmt";
    }
}

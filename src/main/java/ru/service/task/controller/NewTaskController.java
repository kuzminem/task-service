package ru.service.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.service.task.controller.entity.TaskDto;
import ru.service.task.service.NewTaskService;

@Controller
@RequestMapping(value = "/new")
public class NewTaskController {

    private final NewTaskService newTaskService;

    @Autowired
    public NewTaskController(NewTaskService newTaskService) {
        this.newTaskService = newTaskService;
    }

    /**
     * Синяя кнопка "+" на корневой странице.
     *
     * @return - переходим в создание заявки.
     */
    @GetMapping
    public String showTasks(Model model) {
        model.addAttribute("users", newTaskService.getAllUsers());
        return "new";
    }

    /**
     * Синяя кнопка "+" на странице /new.
     *
     * @param subject     - получаем тему заявки.
     * @param description - получаем описание заявки.
     * @return - переходим на корневую страницу.
     */
    @PostMapping
    public String newTask(@RequestParam("client") String client,
                          @RequestParam("subject") String subject,
                          @RequestParam("description") String description) {
        TaskDto taskDto = new TaskDto();
        taskDto.setClientId(newTaskService.getClientIdByUsername(client));
        taskDto.setSubject(subject);
        taskDto.setDescription(description);
        newTaskService.newTask(taskDto);

        return "redirect:/";
    }
}

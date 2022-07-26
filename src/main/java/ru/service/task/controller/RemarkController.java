package ru.service.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.service.task.controller.entity.RemarkDto;
import ru.service.task.controller.entity.TaskFront;
import ru.service.task.service.RemarkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/task")
public class RemarkController {

    private final RemarkService remarkService;


    @Autowired
    public RemarkController(RemarkService remarkService) {
        this.remarkService = remarkService;
    }

    @GetMapping
    public String showTask(@RequestParam long id, Model model) {
        TaskFront taskFront = remarkService.getTaskBuId(id);
        model.addAttribute("task", taskFront);
        List<String> executors = new ArrayList<>();
        if (taskFront.getStatus().equals("Исп. не назначен")) {
            executors.add("Исп. не назначен");
        }
        executors.addAll(remarkService.getExecutors());
        model.addAttribute("executors", executors);
        List<String> statuses = remarkService.getStatuses();
        if (!taskFront.getExecutor().equals("Исп. не назначен")) {
            statuses.remove("Исп. не назначен");
        }
        model.addAttribute("statuses", statuses);
        model.addAttribute("remarks", remarkService.getRemarks(id));

        return "task";
    }

    /**
     * Синяя кнопка "+" на странице /task.
     */
    @PostMapping
    public String newRemark(@RequestParam Map<String, String> params) {
        long taskId = Long.parseLong(params.get("taskId"));
        RemarkDto remarkDto = new RemarkDto();
        remarkDto.setTaskId(taskId);
        remarkDto.setAuthorId(remarkService.getAuthorId(params.get("author")));
        if (params.containsKey("executor")) {
            if (!remarkService.updateExecutor(remarkDto, params.get("executor"))) {
                remarkService.updateStatus(remarkDto, params.get("status"));
            }
        }
        remarkDto.setRemark(params.get("remark"));
        remarkService.addRemark(remarkDto);
        String url = "redirect:/task?id=" + taskId;
        return url;
    }
}

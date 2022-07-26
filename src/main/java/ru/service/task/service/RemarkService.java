package ru.service.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.service.task.controller.entity.RemarkDto;
import ru.service.task.controller.entity.RemarkFront;
import ru.service.task.controller.entity.TaskFront;
import ru.service.task.repository.RemarkRepository;
import ru.service.task.service.mapper.RemarkMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RemarkService {

    private final RemarkRepository remarkRepository;
    private final RemarkMapper remarkMapper;
    private final TaskService taskService;
    private final UserService userService;
    private final StatusService statusService;
    private final HelpdeskService helpdeskService;


    @Autowired
    public RemarkService(RemarkRepository remarkRepository,
                         RemarkMapper remarkMapper,
                         TaskService taskService,
                         UserService userService, StatusService statusService, HelpdeskService helpdeskService) {
        this.remarkRepository = remarkRepository;
        this.remarkMapper = remarkMapper;
        this.taskService = taskService;
        this.userService = userService;
        this.statusService = statusService;
        this.helpdeskService = helpdeskService;
    }

    /**
     * Добавить комментарий к заявке.
     */
    public void addRemark(RemarkDto remarkDto) {
        remarkDto.setRemarkDate(LocalDateTime.now());
        remarkRepository.save(remarkMapper.toRemarkDao(remarkDto));
    }

    /**
     * Получаем список комментариев к заявке.
     *
     * @param taskId - номер заявки.
     * @return - список комментариев с task_id = taskId.
     */
    public List<RemarkFront> getRemarks(long taskId) {
        return map(remarkMapper.map(remarkRepository.findByTaskIdEquals(taskId)));
    }

    /**
     * Получить заявку из public.tasks.
     */
    public TaskFront getTaskBuId(long id) {
        return helpdeskService.toTaskFront(taskService.getTaskById(id));
    }

    /**
     * Получаем id автора комментария.
     */
    public long getAuthorId(String author) {
        return userService.getUserId(author);
    }

    /**
     * Получаем список исполнителей + "Исп. не назначен"
     */
    public List<String> getExecutors() {
        return userService.getExecutors();
    }

    /**
     * Получаем список статусов.
     */
    public List<String> getStatuses() {
        return statusService.getAll();
    }

    /**
     * Сравниваем исполнителей и обновляем, если изменился.
     */
    public boolean updateExecutor(RemarkDto remarkDto, String executor) {
        long executorId = userService.getUserId(executor);
        if (executorId == 0) {
            executorId = remarkDto.getAuthorId();
        }
        if (executorId == taskService.getTaskById(remarkDto.getTaskId()).getExecutorId()) {
            return false;
        }
        taskService.updateExecutor(remarkDto.getTaskId(), executorId);
        remarkDto.setRemark("Исполнитель изменён на " + userService.getUsername(executorId) + ", статус изменён на \"В работе\"");
        addRemark(remarkDto);

        return true;
    }

    /**
     * Сравниваем статусы и обновляем, если изменился.
     */
    public boolean updateStatus(RemarkDto remarkDto, String status) {
        long statusId = statusService.getStatusId(status);
        if (statusId == taskService.getTaskById(remarkDto.getTaskId()).getStatusId()) {
            return false;
        }
        taskService.updateStatus(remarkDto.getTaskId(), statusId);
        remarkDto.setRemark("Статус изменён на \"" + status + "\"");
        addRemark(remarkDto);

        return true;
    }

    /**
     * Собираем комментарий, который не стыдно показать.
     */
    public RemarkFront toRemarkFront(RemarkDto remarkDto) {
        if (remarkDto == null) {
            return null;
        }

        RemarkFront remarkFront = new RemarkFront();

        remarkFront.setRemarkDate(formatDate(remarkDto.getRemarkDate()));
        remarkFront.setRemark(remarkDto.getRemark());
        remarkFront.setAuthor(userService.getUsername(remarkDto.getAuthorId()));

        return remarkFront;
    }

    /**
     * Форматирование LocalDateTime.
     *
     * @param localDateTime - дата из public.remarks.
     * @return - дата в виде текста "дд.ММ.гггг ЧЧ:мм"
     */
    private String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    /**
     * Преобразование списка RemarkDao в список RemarkFront.
     */
    public List<RemarkFront> map(List<RemarkDto> remarks) {
        if (remarks == null) {
            return null;
        }

        List<RemarkFront> list = new ArrayList<>();
        for (RemarkDto remark : remarks) {
            list.add(toRemarkFront(remark));
        }

        return list;
    }
}

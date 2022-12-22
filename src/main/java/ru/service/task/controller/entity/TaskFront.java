package ru.service.task.controller.entity;

/**
 * Заявка для передачи в Thymeleaf-шаблон.
 */
public class TaskFront {

    private long id;

    private String client;

    public String subject;

    private String description;

    private String filingDate;

    private String completionDate;

    private String status;

    private String executor;

    public TaskFront() {
    }

    public long getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getFilingDate() {
        return filingDate;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public String getStatus() {
        return status;
    }

    public String getExecutor() {
        return executor;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}

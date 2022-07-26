package ru.service.task.controller.entity;

import java.time.LocalDateTime;

public class TaskDto {

    private long id;

    private long clientId;

    private String subject;

    private String description;

    private LocalDateTime filingDate;

    private LocalDateTime completionDate;

    private long statusId;

    private long executorId;

    public TaskDto() {
    }

    public long getId() {
        return id;
    }

    public long getClientId() {
        return clientId;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getFilingDate() {
        return filingDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public long getStatusId() {
        return statusId;
    }

    public long getExecutorId() {
        return executorId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilingDate(LocalDateTime filingDate) {
        this.filingDate = filingDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public void setExecutorId(long executorId) {
        this.executorId = executorId;
    }
}

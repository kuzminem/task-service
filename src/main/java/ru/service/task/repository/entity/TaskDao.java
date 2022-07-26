package ru.service.task.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.tasks")
public class TaskDao {

    /**
     * Идентификатор (номер) заявки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Клиент по заявке (id пользователя из таблицы users).
     */
    @Column(name = "client_id")
    private long clientId;

    /**
     * Тема заявки.
     */
    @Column(name = "subject")
    private String subject;

    /**
     * Описание.
     */
    @Column(name = "description")
    private String description;

    /**
     * Дата создания заявки.
     */
    @Column(name = "filing_date")
    private LocalDateTime filingDate;

    /**
     * Дата выполнения заявки.
     */
    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    /**
     * В каком статусе находится заявка (id из таблицы statuses).
     */
    @Column(name = "status_id")
    private long statusId;

    /**
     * Исполнитель по заявке (id пользователя из таблицы users).
     */
    @Column(name = "executor_id")
    private long executorId;

    public TaskDao() {
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

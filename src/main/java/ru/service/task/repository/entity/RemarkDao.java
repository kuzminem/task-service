package ru.service.task.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.remarks")
public class RemarkDao {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Идентификатор (номер) заявки.
     */
    @Column(name = "task_id")
    private long taskId;

    /**
     * Дата комментария.
     */
    @Column(name = "remark_date")
    private LocalDateTime remarkDate;

    /**
     * Текст комментария.
     */
    @Column(name = "remark")
    private String remark;

    /**
     * Автор комментария.
     */
    @Column(name = "author_id")
    private long authorId;

    public RemarkDao() {
    }

    public long getId() {
        return id;
    }

    public long getTaskId() {
        return taskId;
    }

    public LocalDateTime getRemarkDate() {
        return remarkDate;
    }

    public String getRemark() {
        return remark;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public void setRemarkDate(LocalDateTime remarkDate) {
        this.remarkDate = remarkDate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}

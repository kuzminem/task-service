package ru.service.task.controller.entity;

import java.time.LocalDateTime;

public class RemarkDto {

    private long id;

    private long taskId;

    private LocalDateTime remarkDate;

    private String remark;

    private long authorId;

    public RemarkDto() {
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

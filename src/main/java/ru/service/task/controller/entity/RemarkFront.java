package ru.service.task.controller.entity;

public class RemarkFront {

    /**
     * Дата комментария.
     */
    private String remarkDate;

    /**
     * Текст комментария.
     */
    private String remark;

    /**
     * Автор комментария.
     */
    private String author;

    public RemarkFront() {
    }

    public String getRemarkDate() {
        return remarkDate;
    }

    public String getRemark() {
        return remark;
    }

    public String getAuthor() {
        return author;
    }

    public void setRemarkDate(String remarkDate) {
        this.remarkDate = remarkDate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

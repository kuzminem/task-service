package ru.service.task.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.statuses")
public class StatusDao {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "status")
    private String status;

    public StatusDao() {
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

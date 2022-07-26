package ru.service.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.service.task.repository.entity.RemarkDao;

import java.util.List;

public interface RemarkRepository extends JpaRepository<RemarkDao, Long> {

    @Query("select r from RemarkDao r where r.taskId = :taskId order by r.id DESC")
    List<RemarkDao> findByTaskIdEquals(@Param("taskId") long taskId);
}

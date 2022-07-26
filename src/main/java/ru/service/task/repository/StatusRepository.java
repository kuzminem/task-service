package ru.service.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.service.task.repository.entity.StatusDao;

public interface StatusRepository extends JpaRepository<StatusDao, Long> {
    @Query("select s from StatusDao s where s.status = :status")
    StatusDao findByStatusEquals(@Param("status") String status);
}

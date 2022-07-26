package ru.service.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.service.task.repository.entity.TaskDao;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskDao, Long> {

    @Query("select t from TaskDao t order by t.id DESC")
    List<TaskDao> getAll();

    @Query("select t from TaskDao t where "
            + "t.clientId = :clientId and "
            + "t.statusId = :status "
            + "order by t.id DESC")
    List<TaskDao> getUsersTask(@Param("clientId") long clientId, @Param("status") long status);

    @Query("select t from TaskDao t where "
            + "t.clientId = :clientId and "
            + "t.statusId > 0 and t.statusId < 4 "
            + "order by t.id DESC")
    List<TaskDao> getUsersTask0(@Param("clientId") long clientId);

    @Query("select t from TaskDao t where "
            + "t.clientId = :clientId "
            + "order by t.id DESC")
    List<TaskDao> getUsersTask5(@Param("clientId") long clientId);

    @Query("select t from TaskDao t where "
            + "(t.executorId = :executorId or t.executorId = 0) and "
            + "t.statusId = :status "
            + "order by t.id DESC")
    List<TaskDao> getExecutorsTask(@Param("executorId") long executorId, @Param("status") long status);

    @Query("select t from TaskDao t where "
            + "(t.executorId = :executorId or t.executorId = 0) and "
            + "(t.statusId > 0 and t.statusId < 4) "
            + "order by t.id DESC")
    List<TaskDao> getExecutorsTask0(@Param("executorId") long executorId);

    @Query("select t from TaskDao t where "
            + "t.executorId = :executorId or t.executorId = 0 "
            + "order by t.id DESC")
    List<TaskDao> getExecutorsTask5(@Param("executorId") long executorId);

    @Transactional
    @Modifying
    @Query("update TaskDao t set t.executorId = :executorId, t.statusId = 2 where t.id = :id")
    void updateExecutor(@Param("id") long id,
                        @Param("executorId") long executorId);

    @Transactional
    @Modifying
    @Query("update TaskDao t set t.statusId = :statusId where t.id = :id")
    void updateTaskStatus(@Param("id") long id,
                          @Param("statusId") long statusId);
}

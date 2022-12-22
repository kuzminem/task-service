package ru.service.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.service.task.repository.entity.UserDao;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDao, Long> {

    /**
     * Для Spring Security.
     */
    UserDao findByUsernameEquals(String username);

    @Transactional
    @Modifying
    @Query("update UserDao u set "
            + "u.password = :password, "
            + "u.credentials = :credentials, "
            + "u.lastName = :lastName, "
            + "u.firstName = :firstName, "
            + "u.secondName = :secondName "
            + "where u.id = :id")
    void updateUser(@Param("id") long id,
                    @Param("password") String password,
                    @Param("credentials") String credentials,
                    @Param("lastName") String lastName,
                    @Param("firstName") String firstName,
                    @Param("secondName") String secondName);

    @Query("select u from UserDao u")
    List<UserDao> getAll();

    @Query("select u from UserDao u where u.credentials = 'ROLE_EXECUTOR'")
    List<UserDao> getExecutors();

    @Query("select u from UserDao u where u.username = :username")
    UserDao getUserByUsernameNormal(@Param("username") String username);

    @Query("select (count(u) > 0) from UserDao u where u.username = :username")
    boolean existsByUsernameEquals(@Param("username") String username);
}

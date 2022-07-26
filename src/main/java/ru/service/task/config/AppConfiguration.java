package ru.service.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import ru.service.task.service.mapper.RemarkMapper;
import ru.service.task.service.mapper.RemarkMapperImpl;
import ru.service.task.service.mapper.TaskMapper;
import ru.service.task.service.mapper.TaskMapperImpl;
import ru.service.task.service.mapper.UserMapper;
import ru.service.task.service.mapper.UserMapperImpl;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Конфигурация.
 */
@Configuration
@EnableJpaRepositories(basePackages = "ru.service.task.repository")
public class AppConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/db");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("user");
        dataSource.setPassword("pass");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        factoryBean.setJpaProperties(properties);
        factoryBean.setPackagesToScan("ru.service.task.repository.entity");
        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapperImpl();
    }

    @Bean
    public RemarkMapper remarkMapper() {
        return new RemarkMapperImpl();
    }
}

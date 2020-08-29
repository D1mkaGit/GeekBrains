package ru.geekbrains.chat.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SpringConfig {

    @Bean
    public UserRepository userRepository(DataSource dataSource) throws SQLException {
        return new UserRepository(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:users.db");
        return ds;
    }

    @Bean
    public Server Server() {
        return new Server();
    }

}

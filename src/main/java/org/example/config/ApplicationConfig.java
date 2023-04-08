package org.example.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "org.example")
public class ApplicationConfig {
    //Внедрить данный бин внутрь UserDaoImpl, чтобы получать данные из бд.
    @Bean
    public DataSource dataSource() throws SQLException {
        //Введите ваши данные для бд
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerName("localhost");
        source.setPortNumber(1235);
        source.setDatabaseName("test");
        source.setUser("postgres");
        source.setPassword("123");

        return source;
    }
}

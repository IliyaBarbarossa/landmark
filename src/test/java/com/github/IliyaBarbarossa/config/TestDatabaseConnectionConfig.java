package com.github.IliyaBarbarossa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class TestDatabaseConnectionConfig {

    @SneakyThrows
    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;


        config.setJdbcUrl("jdbc:tc:pgvector:pg15:///CityExcursions");
        config.setDriverClassName(org.testcontainers.jdbc.ContainerDatabaseDriver.class.getCanonicalName());
        ds = new HikariDataSource(config);

        Connection connection = ds.getConnection();
        JdbcConnection jdbcConnection = new JdbcConnection(connection);
        liquibase.database.Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {

            CommandScope update = new CommandScope("update");

            update.addArgumentValue("changeLogFile", "changelog.sql");
            update.addArgumentValue("database", database);
            update.execute();
            jdbcConnection.close();
        });
        return ds;
    }

}

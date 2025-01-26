package com.github.IliyaBarbarossa.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
@ComponentScan(basePackages = "com.github.IliyaBarbarossa",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.github.IliyaBarbarossa.controllers.*")})
@EnableJpaRepositories("com.github.IliyaBarbarossa.repositories")
@EnableTransactionManagement
public class AppConfig {

    @SneakyThrows
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;


        config.setJdbcUrl("jdbc:postgresql://localhost:5432/CityExcursions?currentSchema=exc");
        config.setUsername("melkii");
        config.setPassword("postgres");
        config.setDriverClassName(org.postgresql.Driver.class.getCanonicalName());
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

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource);
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("com.github.IliyaBarbarossa.model");
        return lemfb;
    }

}

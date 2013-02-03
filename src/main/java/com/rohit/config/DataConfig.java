package com.rohit.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@ComponentScan(basePackages = "com.rohit.repository")
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(jpaVendorProvider());
        factory.setPersistenceUnitName("my_repo_under_test");
        factory.setPackagesToScan("com.rohit.domain");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(jpaProperties());
        return factory;
    }

    @Bean
    public SharedEntityManagerBean sharedEntityManagerBean() {
        final SharedEntityManagerBean entityManagerBean = new SharedEntityManagerBean();
        entityManagerBean.setEntityManagerFactory(entityManagerFactory().getObject());
        return entityManagerBean;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringSessionContext");
        return properties;
    }

    private HibernateJpaVendorAdapter jpaVendorProvider() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.H2);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }
}

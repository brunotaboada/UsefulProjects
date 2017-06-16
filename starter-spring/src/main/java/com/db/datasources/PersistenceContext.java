package com.db.datasources;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "com.db.repository" })
@EnableTransactionManagement
class PersistenceContext {

    @Autowired
    Environment env;

    @Autowired
    DataSource datasource;

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(datasource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.db.entities");

        Properties jpaProperties = new Properties();

        // Configures the used database dialect. This allows Hibernate to create
        // SQL
        // that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

        // Specifies the action that is invoked to the database when the
        // Hibernate
        // SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        // Configures the naming strategy that is used when Hibernate creates
        // new database objects and schema elements
        // jpaProperties.put("hibernate.ejb.naming_strategy",
        // env.getProperty("hibernate.ejb.naming_strategy"));

        // If the value of this property is true, Hibernate writes all SQL
        // statements to the console.
        // jpaProperties.put("hibernate.show_sql",
        // env.getProperty("hibernate.show_sql"));

        // If the value of this property is true, Hibernate will format the SQL
        // that is written to the console.
        // jpaProperties.put("hibernate.format_sql",
        // env.getProperty("hibernate.format_sql"));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

}

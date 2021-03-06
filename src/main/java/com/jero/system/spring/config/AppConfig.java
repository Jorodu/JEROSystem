package com.jero.system.spring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jero.system.spring.model.Authorities;
import com.jero.system.spring.model.User;

import static org.hibernate.cfg.Environment.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.jero.system.spring.dao"),
    @ComponentScan("com.jero.system.spring.service") })
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class AppConfig {

    
  @Autowired
  private Environment env;

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    
    Properties props = new Properties();
    
    // Setting JDBC properties
    props.put(DRIVER, env.getProperty("mysql.driver"));
    props.put(URL, env.getProperty("mysql.jdbcUrl"));
    props.put(USER, env.getProperty("mysql.username"));
    props.put(PASS, env.getProperty("mysql.password"));

    // Setting Hibernate properties
    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
    props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
    props.put(DIALECT, env.getProperty("hibernate.dialect"));

    // Setting C3P0 properties
    props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
    props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
    props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
    props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
    props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

    factoryBean.setHibernateProperties(props);
    factoryBean.setAnnotatedClasses(User.class, Authorities.class);
    
    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(getSessionFactory().getObject());
    return transactionManager;
  }
}

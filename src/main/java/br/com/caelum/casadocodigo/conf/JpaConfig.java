package br.com.caelum.casadocodigo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
public class JpaConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource);
        managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        managerFactoryBean.setJpaProperties(myJpaConfig());
        managerFactoryBean.setPackagesToScan("br.com.caelum.casadocodigo.models");
        return managerFactoryBean;
    }

    private Properties myJpaConfig() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        return properties;
    }

    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("caelum");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }
}

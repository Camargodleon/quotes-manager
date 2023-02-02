package com.inatel.quotationmanagement.configurations;

import com.inatel.quotationmanagement.entities.Stock;
import com.inatel.quotationmanagement.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.UUID;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlentityManagerFactory", transactionManagerRef = "mysqltransactionManager",
        basePackages = {"com.inatel.quotationmanagement.entites", "com.inatel.quotationmanagement.repositories"})
public class MySQLConfiguration {

    @Primary
    @Bean(name = "mysqldataSource")
    @ConfigurationProperties(prefix = "spring.mysqldatasource")
    public DataSource datasource(){
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name = "mysqlentityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("mysqldataSource") DataSource dataSource){
        return entityManagerFactoryBuilder.dataSource(dataSource).packages("com.inatel.quotationmanagement.entities").persistenceUnit("mysql").build();
    }
    @Primary
    @Bean(name = "mysqltransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("mysqlentityManagerFactory")EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }



}

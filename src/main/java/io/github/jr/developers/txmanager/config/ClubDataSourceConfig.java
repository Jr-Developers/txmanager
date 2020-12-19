package io.github.jr.developers.txmanager.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
        basePackages = {"io.github.jr.developers.txmanager.club"},
        entityManagerFactoryRef = "clubEntityManagerFactory",
        transactionManagerRef = "clubTransactionManager"
)
    public class ClubDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties clubDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource1.hikari")
    public HikariDataSource clubHikariDataSource() {
        return clubDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean clubEntityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setDataSource(clubHikariDataSource());
        factoryBean.setPackagesToScan("io.github.jr.developers.txmanager.club");
        factoryBean.setPersistenceUnitName("clubEntityManager");
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager clubTransactionManager(@Qualifier("clubEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}


package com.team1159ers.mock_csulb_db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collections;

/**
 * Configuration file for managing the mock CSULB database.
 * When multiple databases are defined, JPA Springboot does not automatically know which database
 * is the default to use, or which one refers to which class when attempting to persist data.
 * Here, we specifically define the server port, data source extension, the entity manager,
 * transaction manager, and packages the database will be using as reference when persisting our entities.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mockDataEntityManagerFactory",
        transactionManagerRef = "mockDataTransactionManager",
        basePackages = {
                "com.team1159ers.mock_csulb_db.repository.user",
                "com.team1159ers.mock_csulb_db.repository.admin" })
public class MockCsulbDbConfiguration implements WebMvcConfigurer,
        WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    /**
     * Whenever a resource wants to interact with the server, they will do so through
     * HTTP requests; however, the client and server are often under different origins.
     * CORS (Cross-origin Resource Sharing) enables you to access a resource from a
     * different origin. It is used to override your browser's default behavior due to
     * SOP (Same-Origin policy). So now when your client requests a resource, the response
     * will additionally contain a stamp that tells your browser to allow resource sharing
     * across different origins.
     * The client doesn't have anything to do with CORS; it's only something that your browser
     * imposes, and it suggests that your requested resource should be configured differently.
     * Therefore, it makes sense to configure the response from the server in such a way that
     * the browser identifies this as a CORS request. Hence, logically, CORS should always be
     * handled from the server side.
     * @param registry the mappings of paths where we are allowing connections through CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE");
    }

    @Bean(name="mockDataProps")
    @ConfigurationProperties(value = "spring.datasource2")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name="datasource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource datasource(@Qualifier("mockDataProps") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name="mockDataEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("datasource2") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("com.team1159ers.mock_csulb_db.model.admin",
                        "com.team1159ers.mock_csulb_db.model.user")
                .persistenceUnit("mockData").build();
    }

    @Bean(name = "mockDataTransactionManager")
    @ConfigurationProperties("spring.jpa1")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mockDataEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8081);
    }
}

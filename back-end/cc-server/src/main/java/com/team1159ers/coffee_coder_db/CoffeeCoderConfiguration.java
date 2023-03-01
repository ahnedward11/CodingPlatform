
package com.team1159ers.coffee_coder_db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Configuration file for managing the Coffee Coder database.
 * When multiple databases are defined, JPA Springboot does not automatically know which database
 * is the default to use, or which one refers to which class when attempting to persist data.
 * Here, we specifically define the server port, data source extension, the entity manager,
 *  * transaction manager, and packages the database will be using as reference when persisting our entities.
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "coffeeCoderEntityManagerFactory",
        transactionManagerRef = "coffeeCoderTransactionManager",
        basePackages = {
                "com.team1159ers.coffee_coder_db.repository.user",
                "com.team1159ers.coffee_coder_db.repository.dailyexercise",
                "com.team1159ers.coffee_coder_db.repository.dailyexerciseexample",
                "com.team1159ers.coffee_coder_db.repository.dailyexercisetestcase",
                "com.team1159ers.coffee_coder_db.repository.codingproblem",
                "com.team1159ers.coffee_coder_db.repository.codingproblemexample",
                "com.team1159ers.coffee_coder_db.repository.codingproblemtestcase",
                "com.team1159ers.coffee_coder_db.repository.userdailyexercise",
                "com.team1159ers.coffee_coder_db.repository.usercodingproblem",
                "com.team1159ers.coffee_coder_db.repository.note",
                "com.team1159ers.coffee_coder_db.repository.admin",
                "com.team1159ers.coffee_coder_db.repository.dailyexercisesolution",
                "com.team1159ers.coffee_coder_db.repository.codingproblemsolution",
                "com.team1159ers.coffee_coder_db.repository.report" })
public class CoffeeCoderConfiguration implements WebMvcConfigurer,
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

    @Primary
    @Bean(name="coffeeCoderProps")
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name="datasource1")
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource datasource(@Qualifier("coffeeCoderProps") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name="coffeeCoderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("datasource1") DataSource dataSource){
        return builder.dataSource(dataSource)
                .packages("com.team1159ers.coffee_coder_db.model.user",
                        "com.team1159ers.coffee_coder_db.model.dailyexercise",
                        "com.team1159ers.coffee_coder_db.model.dailyexerciseexample",
                        "com.team1159ers.coffee_coder_db.model.dailyexercisetestcase",
                        "com.team1159ers.coffee_coder_db.model.codingproblem",
                        "com.team1159ers.coffee_coder_db.model.codingproblemexample",
                        "com.team1159ers.coffee_coder_db.model.codingproblemtestcase",
                        "com.team1159ers.coffee_coder_db.model.userdailyexercise",
                        "com.team1159ers.coffee_coder_db.model.usercodingproblem",
                        "com.team1159ers.coffee_coder_db.model.note",
                        "com.team1159ers.coffee_coder_db.model.admin",
                        "com.team1159ers.coffee_coder_db.model.dailyexercisesolution",
                        "com.team1159ers.coffee_coder_db.model.codingproblemsolution",
                        "com.team1159ers.coffee_coder_db.model.report",
                        "com.team1159ers.coffee_coder_db.model.code"
                    )
                .persistenceUnit("coffeeCoderData").build();
    }

    @Primary
    @Bean(name = "coffeeCoderTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("coffeeCoderEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(8080);
    }
}

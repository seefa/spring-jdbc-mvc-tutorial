package ir.seefa.tutorial.spring.config;

import ir.seefa.tutorial.spring.exception.CustomSqlErrorCodeTranslator;
import ir.seefa.tutorial.spring.repository.ContactDao;
import ir.seefa.tutorial.spring.repository.ContactDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 09 Sep 2020 T 23:54:03
 */
// 1. Read spring-core-tutorial and spring-jdbc-tutorial codes before starting this project because primary annotations and jdbc logic explained there
@Configuration
@ComponentScan(basePackages = "ir.seefa.tutorial.spring")
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
// 2. configuration to enable Spring WebMvc module
@EnableWebMvc
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    @Value("${jdbc.driver.class}")
    private String jdbcClassDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    // 3. add ViewResolver bean to config web files to find and files extension
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    // 4. override addResourceHandlers() function from WebMvcConfigurerAdapter parent class to define static web files loacation
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // 5. following bean definition explained in spring-jdbc-tutorial project
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(jdbcClassDriver);
        ds.setUrl(jdbcUrl);
        ds.setUsername(jdbcUsername);
        ds.setPassword(jdbcPassword);

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        template.setExceptionTranslator(new CustomSqlErrorCodeTranslator());
        return template;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert() {
        SimpleJdbcInsert template = new SimpleJdbcInsert(dataSource());
        return template;
    }

    @Bean
    public ContactDao getContactDao() {
        return new ContactDaoImpl();
    }
}

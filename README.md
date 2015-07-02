# Spring Boot Multiple Datasource with Liquibase
## Multiple Datasource
You need to add a couple of datasource definitions to your yaml configuration file.

    datasource:
      primary:
        url: jdbc:mysql://localhost/primary
        username: root
        driver-class-name: com.mysql.jdbc.Driver
        validation-query: select 1
      secondary:
        url: jdbc:mysql://localhost/secondary
        username: root
        driver-class-name: com.mysql.jdbc.Driver
        validation-query: select 1

Make sure to disable the *DataSourceAutoConfiguration* in you configuration

    @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

You then need to manual configure each datasource and remember to set one as primary for any other auto configuration to work.

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcPrimaryTemplate")
    public JdbcTemplate jdbcPrimaryTemplate(@Qualifier(value = "primaryDataSource") DataSource primaryDataSource) {
        return new JdbcTemplate(primaryDataSource);
    }


## Liquibase
### Running Liquibase
All you need to do in a Spring Boot project to get Liquibase to run for you is to simply add a dependency to the project pom. You can read more in the documents.
[Liquibase Database Initialization](http://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html)

### Liquibase Properties
Looking at the spring documentation you can find the additional properties used for Liquibase.
In this example I am explicitly stating the connection properties rather than attempt to use the default datasource.
[Spring Ref Appendix A. ](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
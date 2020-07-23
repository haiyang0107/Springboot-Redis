English | [简体中文](./readMe-ZH.md)

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#build-image)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

### Guides
The following guides illustrate how to use some features concretely:

* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)

This project is mainly about the use of handwritten SQL, and through the way of configuration files, to achieve the management of multiple data sources!

Using connection pool for data source management:
## Project architecture
````
  │─sql                   (Database script)   
  ├─src                  （Back end folder）
  │  ├─main              （Basic kernel）
      └─java             （Specific implementation code package）
      └─resources        （Configuration folder）
````
### application.yml
````
    adopt application.yml Basic configuration information for database connection, including multi data source configuration：
    spring:
      datasource:
        driver-class-name: com.mysql.jdbc.Driver
        #Connection pool
        druid:
           mybatis: #Name customization (the first primary data source by default)
              url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT//serverTimezone=GMT mysql8.0 
              username: root
              password: 12345678
           world:
              url: jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT
              username: root
              password: 12345678
````

### masterDataSourceConfig Master data source configuration
````
 /**
  * Master data source profile
  */
 @Configuration
 //Interface and container
 @MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
 public class MasterDataSourceConfig {
     // Accurate to the master directory for isolation from other data sources
     static final String PACKAGE = "com.springboot.configdemo.dao.master";
     static final String MAPPER_LOCATION = "classpath:mybatis/*.xml"; //Define the SQL mapping file address of the master data source
 
     @Value("${spring.datasource.druid.mybatis.url}")
     private String url;
 
     @Value("${spring.datasource.druid.mybatis.username}")
     private String user;
 
     @Value("${spring.datasource.druid.mybatis.password}")
     private String password;
 
     @Value("${spring.datasource.driver-class-name}")
     private String driverClass;
 
     @Bean(name = "masterDataSource") //（To define the datasource name, it is necessary to ensure that it is unique）
     @Primary //Define the master database ID, which is globally unique
     public DataSource masterDataSource() {
         DruidDataSource dataSource = new DruidDataSource();
         dataSource.setDriverClassName(driverClass);
         dataSource.setUrl(url);
         dataSource.setUsername(user);
         dataSource.setPassword(password);
         return dataSource;
     }
 
     @Bean(name = "masterTransactionManager")
     @Primary
     public DataSourceTransactionManager masterTransactionManager() {
         return new DataSourceTransactionManager(masterDataSource());
     }
 
     @Bean(name = "masterSqlSessionFactory")
     @Primary
     public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
             throws Exception {
         final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
         sessionFactory.setDataSource(masterDataSource);
         sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                 .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
         return sessionFactory.getObject();
     }
 }   
````
### secondDataSourceConfig  Configure from data source
````
/**
 * Configure from data source
 */
@Configuration
//Interface and container
@MapperScan(basePackages = SecondDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {
    // Accurate to the master directory for isolation from other data sources
    static final String PACKAGE = "com.springboot.configdemo.dao.second";
    static final String MAPPER_LOCATION = "classpath:second/*.xml";

    @Value("${spring.datasource.druid.world.url}")
    private String url;

    @Value("${spring.datasource.druid.world.username}")
    private String user;

    @Value("${spring.datasource.druid.world.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "secondDataSource")
    public DataSource clusterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("secondDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SecondDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
````
-At this point, more than half of the main configuration of multiple data sources is solved, and the rest only needs to define and edit the interface according to the integration of springboot and mybatis!
- On the Internet, there are many multi data source multi data source configuration demo, but most of them will leave a bug or step on the pit. The best way is to test it yourself and write a demo
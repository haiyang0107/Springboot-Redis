[English](./readMe.md) | 简体中文
# 入门

### 参考文件
为了进一步参考，请考虑以下章节:

* [入门版Apache Maven官方文档](https://maven.apache.org/guides/index.html)
* [springbootmaven插件参考指南](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/)
* [创建OCI映像](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#build-image)
* [MyBatis框架](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

### 指南
以下指南将具体说明如何使用某些功能:

* [MyBatis快速开始](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)

本项目主要是关于使用手写sql,并通过配置文件的方式，实现对多数据源的管理！

使用连接池进行数据源的管理：
## 项目架构
````
  │─sql                   (数据库脚本)   
  ├─src                  （后端文件夹）
  │  ├─main              （基础内核）
      └─java             （具体实现代码包）
      └─resources        （配置文件夹）
````
### application.yml
````
    通过applica.yml 进行数据库连接的基本配置信息，包含多数据源配置：
    spring:
      datasource:
        driver-class-name: com.mysql.jdbc.Driver
        #连接池
        druid:
           mybatis: #名称自定义（默认第一个为主数据源）
              url: jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT//serverTimezone=GMT mysql8.0 必加
              username: root
              password: 12345678
           world:
              url: jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT
              username: root
              password: 12345678
````

### masterDataSourceConfig 主数据源配置
````
 /**
  * 主数据源配置文件
  */
 @Configuration
 //接口并容器
 @MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
 public class MasterDataSourceConfig {
     // 精确到 master 目录，以便跟其他数据源隔离
     static final String PACKAGE = "com.springboot.configdemo.dao.master";//定义主数据源的mapper接口路径
     static final String MAPPER_LOCATION = "classpath:mybatis/*.xml"; //定义主数据源的sql映射文件地址
 
     @Value("${spring.datasource.druid.mybatis.url}")
     private String url;
 
     @Value("${spring.datasource.druid.mybatis.username}")
     private String user;
 
     @Value("${spring.datasource.druid.mybatis.password}")
     private String password;
 
     @Value("${spring.datasource.driver-class-name}")
     private String driverClass;
 
     @Bean(name = "masterDataSource") //（定义DataSource名称，需要保证唯一）
     @Primary //定义主库标识，全局唯一，
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
### secondDataSourceConfig  从数据源配置
````
/**
 * 从数据源配置
 */
@Configuration
//接口并容器
@MapperScan(basePackages = SecondDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {
    // 精确到 cluster 目录，以便跟其他数据源隔离
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
- 至此，关于多数据源的主要配置就解决了一大半，剩下的只需要按照springboot 与mybatis 的整合进行接口的定义以及编辑即可！
- 关于网上，有很多多数据源的多数据源的配置demo，但是大部分都会留下一个BUG 或者 踩坑 , 最好的方式还是自己亲测一遍，手写demo
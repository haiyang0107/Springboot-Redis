package com.example.shardingdemo.config;

import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @Auther: huhy
 * @Date: 2020/7/15 17:51
 * @Description:
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private DataBase0Config database0Config;

    @Autowired
    private DataBase1Config database1Config;

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @Primary
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        //分库设置
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        //添加两个数据库database0和database1
        dataSourceMap.put(database0Config.getDatabaseName(), database0Config.createDataSource());
        dataSourceMap.put(database1Config.getDatabaseName(), database1Config.createDataSource());

        KeyGeneratorConfiguration keyGeneratorConfiguration = new KeyGeneratorConfiguration("SNOWFLAKE","id");//主键生成策略
        List<TableRuleConfiguration> tableRuleConfigurations = new ArrayList<TableRuleConfiguration>();

        //分表设置，大致思想就是将查询虚拟表Goods根据一定规则映射到真实表中去
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("t_dict","ds$->{0..1}.t_dict_$->{0..3}");//分表规则
        tableRuleConfiguration.setKeyGeneratorConfig(keyGeneratorConfiguration);//设定主键分库策略
        StandardShardingStrategyConfiguration standardShardingStrategyConfiguration = new StandardShardingStrategyConfiguration("id", new PreciseShardingAlgorithm() {
            @Override
            public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
                Long value =Long.parseLong(preciseShardingValue.getValue().toString());
                //TODO 自定义分表规则，灵活添加分表规则，减轻服务压力,可以后续添加更多分表规则，实现数据库减压
                if (value % 2 == 0) {
                    return "t_dict_0";
                } else {
                    return  "t_dict_1";
                }
            }
        });
        tableRuleConfiguration.setTableShardingStrategyConfig(standardShardingStrategyConfiguration);//table-strategy 制定具体得分表规则
        tableRuleConfigurations.add(tableRuleConfiguration);
        //分库分表策略
        ShardingRuleConfiguration configuration = new  ShardingRuleConfiguration();

        configuration.setTableRuleConfigs(tableRuleConfigurations);//分表规则集

        configuration.setDefaultKeyGeneratorConfig(keyGeneratorConfiguration);//默认主键生成策略

        configuration.setDefaultDataSourceName(database0Config.getDatabaseName());//不分库分表情况下的数据源指定--默认数据库
        configuration.setDefaultTableShardingStrategyConfig(standardShardingStrategyConfiguration);//设置默认得分表规则

        //单键分库规则
        StandardShardingStrategyConfiguration defaultDatabaseShardingStrategyConfig = new StandardShardingStrategyConfiguration("id", new PreciseShardingAlgorithm() {
            @Override
            public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
                Long value =Long.parseLong(preciseShardingValue.getValue().toString());
                if (value % 2 == 0) {
                    return database0Config.getDatabaseName();
                } else {
                    return  database1Config.getDatabaseName();
                }
            }
        });
        configuration.setDefaultDatabaseShardingStrategyConfig(defaultDatabaseShardingStrategyConfig);

        Properties properties = new Properties();
        properties.setProperty(database0Config.getDatabaseName(),database0Config.createDataSource().toString());
        properties.setProperty(database1Config.getDatabaseName(),database1Config.createDataSource().toString());
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, configuration, null);
        return dataSource;
    }



}

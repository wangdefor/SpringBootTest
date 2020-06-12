package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.KeyGeneratorConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.apache.shardingsphere.sharding.strategy.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;
import org.apache.shardingsphere.sharding.strategy.algorithm.sharding.inline.InlineShardingAlgorithm;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName MybatisConfig
 * @Description MybatisConfig
 * @Date 2020/4/14 14:11
 * @Author wangyong
 * @Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "org.example.mapper")
public class MybatisConfig {

    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    private String userName = "root";
    private String password = "12345";

    private String mapperLocation = "classpath:mapper/*.xml";
    private String configLocation = "classpath:mybatis-config.xml";

//    @Bean
//    @Primary
//    public DataSource getDataSource() throws SQLException {
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        // 配置第 1 个数据源
//        DruidDataSource dataSource1 = new DruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/test");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("12345");
//        dataSourceMap.put("ds0", dataSource1);
//
//        // 配置第 2 个数据源
//        DruidDataSource dataSource2 = new DruidDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://10.0.2.8:3306/ways?characterEncoding=utf-8&amp;allowMultiQueries=true");
//        dataSource2.setUsername("cimendev");
//        dataSource2.setPassword("123456");
//        dataSourceMap.put("ds1", dataSource2);
//
//        // 配置 t_order 表规则
//        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order${0..1}");
//
//        // 配置分库策略
//        StandardShardingAlgorithm dbShardingAlgorithm = new InlineShardingAlgorithm();
//        Properties dbProps = new Properties();
//        //按user_id 取2的摸 来选择分库策略
//        dbProps.setProperty("algorithm.expression", "ds${user_id % 2}");
//        dbShardingAlgorithm.setProperties(dbProps);
//        StandardShardingStrategyConfiguration dbShardingStrategyConfig = new StandardShardingStrategyConfiguration("user_id", dbShardingAlgorithm);
//        orderTableRuleConfig.setDatabaseShardingStrategy(dbShardingStrategyConfig);
////
//        // 配置分表策略
//        StandardShardingAlgorithm tableShardingAlgorithm = new InlineShardingAlgorithm();
//        Properties tableProps = new Properties();
//        tableProps.setProperty("algorithm.expression", "t_order${order_id % 2}");
//        tableShardingAlgorithm.setProperties(tableProps);
//
//        StandardShardingStrategyConfiguration tableShardingStrategy = new StandardShardingStrategyConfiguration("order_id", tableShardingAlgorithm);
//        orderTableRuleConfig.setTableShardingStrategy(tableShardingStrategy);
//        //配置雪花算法生成器
//        orderTableRuleConfig.setKeyGenerator(new KeyGeneratorConfiguration("order_id",new SnowflakeKeyGenerateAlgorithm()));
//
//        // 配置 t_order_item 表规则 执行绑定表
//        //配置t_order_item 表规则
//        ShardingTableRuleConfiguration orderItemRuleConfig = new ShardingTableRuleConfiguration("t_order_item", "ds${0..1}.t_order_item${0..1}");
//        orderItemRuleConfig.setTableShardingStrategy(tableShardingStrategy);
//        orderItemRuleConfig.setKeyGenerator(new KeyGeneratorConfiguration("detail_id",new SnowflakeKeyGenerateAlgorithm()));
//
//        // 配置分库策略
//        StandardShardingAlgorithm itemShardingAlgorithm = new InlineShardingAlgorithm();
//        Properties itemPro = new Properties();
//        //按user_id 取2的摸 来选择分库策略
//        itemPro.setProperty("algorithm.expression", "ds${detail_id % 2}");
//        itemShardingAlgorithm.setProperties(itemPro);
//        StandardShardingStrategyConfiguration itemShardingStrategyConfig = new StandardShardingStrategyConfiguration("detail_id", itemShardingAlgorithm);
//        orderItemRuleConfig.setDatabaseShardingStrategy(itemShardingStrategyConfig);
//
//        // 配置分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        Collection<ShardingTableRuleConfiguration> tables = shardingRuleConfig.getTables();
//        tables.add(orderTableRuleConfig);
//        tables.add(orderItemRuleConfig);
//        shardingRuleConfig.setTables(tables);
//        // 创建 ShardingSphereDataSource
//        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Lists.newArrayList(shardingRuleConfig), new Properties());
//        return dataSource;
//    }


    @Bean
    @Primary
    public DataSource getDataSource2() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource1.setUsername("root");
        dataSource1.setPassword("12345");
        dataSourceMap.put("ds0", dataSource1);


        // 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order");

        // 配置分库策略
        StandardShardingAlgorithm dbShardingAlgorithm = new InlineShardingAlgorithm();
        Properties dbProps = new Properties();
        //按user_id 取2的摸 来选择分库策略
        dbProps.setProperty("algorithm.expression", "ds${user_id % 2}");
        dbShardingAlgorithm.setProperties(dbProps);
        StandardShardingStrategyConfiguration dbShardingStrategyConfig = new StandardShardingStrategyConfiguration("user_id", dbShardingAlgorithm);
        orderTableRuleConfig.setDatabaseShardingStrategy(dbShardingStrategyConfig);
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        Collection<ShardingTableRuleConfiguration> tables = shardingRuleConfig.getTables();
        tables.add(orderTableRuleConfig);
        shardingRuleConfig.setTables(tables);
        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory2.createDataSource(dataSourceMap, Lists.newArrayList(shardingRuleConfig), new Properties());
        return dataSource;
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        // 配置mapper的扫描，找到所有的mapper.xml映射文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
        sessionFactoryBean.setMapperLocations(resources);

        // 加载全局的配置文件
        sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        return sessionFactoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}

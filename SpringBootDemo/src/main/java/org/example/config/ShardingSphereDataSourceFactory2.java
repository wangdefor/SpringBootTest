package org.example.config;

import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.database.DefaultSchema;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName ShardingSphereDataSourceFactory2
 * @Description TODO
 * @Date 2020/6/12 15:27
 * @Author wangyong
 * @Version 1.0
 **/
public class ShardingSphereDataSourceFactory2 {

    /**
     * Create ShardingSphere data source.
     *
     * @param dataSourceMap  data source map
     * @param configurations rule configurations
     * @param props          properties for data source
     * @return ShardingSphere data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final Map<String, DataSource> dataSourceMap, final Collection<RuleConfiguration> configurations, final Properties props) throws SQLException {
        return new ShardingSphereDataSource2(dataSourceMap, configurations, props);
    }

    /**
     * Create ShardingSphere data source.
     *
     * @param dataSource     data source
     * @param configurations rule configurations
     * @param props          properties for data source
     * @return ShardingSphere data source
     * @throws SQLException SQL exception
     */
    public static DataSource createDataSource(final DataSource dataSource, final Collection<RuleConfiguration> configurations, final Properties props) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>(1, 1);
        dataSourceMap.put(DefaultSchema.LOGIC_NAME, dataSource);
        return createDataSource(dataSourceMap, configurations, props);
    }

}

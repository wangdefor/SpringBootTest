package org.example.config;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.shardingsphere.driver.jdbc.core.connection.ShardingSphereConnection;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.driver.jdbc.unsupported.AbstractUnsupportedOperationDataSource;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.database.DefaultSchema;
import org.apache.shardingsphere.infra.database.type.DatabaseType;
import org.apache.shardingsphere.infra.database.type.DatabaseTypes;
import org.apache.shardingsphere.kernel.context.SchemaContexts;
import org.apache.shardingsphere.kernel.context.SchemaContextsBuilder;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @ClassName ShardingSphereDataSource2
 * @Description ShardingSphereDataSource2
 * @Date 2020/6/12 15:14
 * @Author wangyong
 * @Version 1.0
 **/
@RequiredArgsConstructor
@Getter
public class ShardingSphereDataSource2 extends AbstractUnsupportedOperationDataSource implements AutoCloseable {


    private SchemaContexts schemaContexts;

    @Setter
    private PrintWriter logWriter = new PrintWriter(System.out);

    @Getter
    private Properties properties;

    @Getter
    private Collection<RuleConfiguration> configurations;

    @Getter
    private Map<String, DataSource> dataSourceMap;

    public ShardingSphereDataSource2(final Map<String, DataSource> dataSourceMap, final Collection<RuleConfiguration> configurations, final Properties props) throws SQLException {
        DatabaseType databaseType = createDatabaseType(dataSourceMap);
        schemaContexts = new SchemaContextsBuilder(Collections.singletonMap(DefaultSchema.LOGIC_NAME, dataSourceMap),
                databaseType, Collections.singletonMap(DefaultSchema.LOGIC_NAME, configurations), props).build();
        this.properties = props;
        this.configurations = configurations;
        this.dataSourceMap = dataSourceMap;
    }

    /**
     * 新增加dataSource
     */
    public void addDataSource(String alias, DataSource dataSource) throws SQLException {
        Assert.isTrue(dataSourceMap != null);
        dataSourceMap.put(alias, dataSource);
        DatabaseType databaseType = createDatabaseType(dataSourceMap);
        schemaContexts = new SchemaContextsBuilder(Collections.singletonMap(DefaultSchema.LOGIC_NAME, dataSourceMap),
                databaseType, Collections.singletonMap(DefaultSchema.LOGIC_NAME, getConfigurations()), getProperties()).build();

    }

    private DatabaseType createDatabaseType(final Map<String, DataSource> dataSourceMap) throws SQLException {
        DatabaseType result = null;
        for (DataSource each : dataSourceMap.values()) {
            DatabaseType databaseType = createDatabaseType(each);
            Preconditions.checkState(null == result || result == databaseType, String.format("Database type inconsistent with '%s' and '%s'", result, databaseType));
            result = databaseType;
        }
        return result;
    }

    private DatabaseType createDatabaseType(final DataSource dataSource) throws SQLException {
        if (dataSource instanceof ShardingSphereDataSource) {
            return ((ShardingSphereDataSource2) dataSource).schemaContexts.getSchemaContexts().get(DefaultSchema.LOGIC_NAME).getSchema().getDatabaseType();
        }
        try (Connection connection = dataSource.getConnection()) {
            return DatabaseTypes.getDatabaseTypeByURL(connection.getMetaData().getURL());
        }
    }

    @Override
    public Logger getParentLogger() {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    @Override
    public ShardingSphereConnection getConnection() {
        return new ShardingSphereConnection(getDataSourceMap(), schemaContexts, TransactionTypeHolder.get());
    }

    @Override
    public ShardingSphereConnection getConnection(final String username, final String password) {
        return getConnection();
    }

    /**
     * Get data sources.
     *
     * @return data sources
     */
    public Map<String, DataSource> getDataSourceMap() {
        return schemaContexts.getSchemaContexts().get(DefaultSchema.LOGIC_NAME).getSchema().getDataSources();
    }

    @Override
    public void close() throws Exception {
        close(getDataSourceMap().keySet());
    }

    /**
     * Close dataSources.
     *
     * @param dataSourceNames data source names
     */
    public void close(final Collection<String> dataSourceNames) {
        dataSourceNames.forEach(each -> close(getDataSourceMap().get(each)));
        schemaContexts.close();
    }

    private void close(final DataSource dataSource) {
        try {
            Method method = dataSource.getClass().getDeclaredMethod("close");
            method.setAccessible(true);
            method.invoke(dataSource);
        } catch (final ReflectiveOperationException ignored) {
        }
    }
}

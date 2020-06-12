package org.example.listener;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.purgeteam.dynamic.config.starter.event.ActionConfigEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.example.config.ShardingSphereDataSource2;
import org.example.model.DataDynamicsSource;
import org.example.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * @ClassName NacosDataSourceListener
 * @Description nacos数据源
 * @Date 2020/6/12 15:51
 * @Author wangyong
 * @Version 1.0
 **/
@Component
@RefreshScope
@Slf4j
public class NacosDataSourceListener implements ApplicationListener<ActionConfigEvent> {

    @Autowired
    private DataDynamicsSource dataDynamicsSource;


    @Autowired
    private DataSource dataSource;

    /**
     * 其实list中的值已经被更新了
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ActionConfigEvent event) {
        System.out.println(event.getEventDesc());
        System.out.println(dataDynamicsSource.getDataModels());
        //将dataDynamicsSource中的数据源与dataSource进行对比
        ShardingSphereDataSource2 source2;
        if (!(dataSource instanceof ShardingSphereDataSource2)) {
            return;
        }
        source2 = (ShardingSphereDataSource2) dataSource;
        //进行动态数据源的添加
        Map<String, DataSource> dataSourceMap = source2.getDataSourceMap();
        for (DataModel model : dataDynamicsSource.getDataModels()) {
            //对dataDynamicsSource进行for循环
            if (!dataSourceMap.containsKey(model.getAlias()) && flag(model)) {
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
                druidDataSource.setUrl(model.getUrl());
                druidDataSource.setUsername(model.getUsername());
                druidDataSource.setPassword(model.getPassword());
                try {
                    source2.addDataSource(model.getAlias(), druidDataSource);
                    dataSource = source2;
                } catch (SQLException e) {
                    e.printStackTrace();
                    log.error("数据源加载错误");
                }
            }
        }
    }

    public Boolean flag(DataModel model) {
        return StringUtils.isNotBlank(model.getPassword())
                && StringUtils.isNotBlank(model.getUrl())
                && StringUtils.isNotBlank(model.getUsername())
                && StringUtils.isNotBlank(model.getAlias());
    }

}

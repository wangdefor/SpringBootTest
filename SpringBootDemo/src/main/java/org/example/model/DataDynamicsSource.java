package org.example.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName DataDynamicsSource
 * @Description DataDynamicsSource
 * @Date 2020/6/12 16:12
 * @Author wangyong
 * @Version 1.0
 **/
@ConfigurationProperties("data.dynamic.source")
@Component
public class DataDynamicsSource {


    private List<DataModel> dataModels;

    public void setDataModels(List<DataModel> dataModels) {
        this.dataModels = dataModels;
    }

    public List<DataModel> getDataModels() {
        return dataModels;
    }
}

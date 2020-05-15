package org.example.service;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @ClassName ClientDetailService
 * @Description JdbcClientDetailsService
 * @Date 2020/5/12 11:16
 * @Author wangyong
 * @Version 1.0
 **/
public class ClientDetailService extends JdbcClientDetailsService {

    public ClientDetailService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}

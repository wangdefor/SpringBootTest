//package org.example.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
////import org.example.handler.CustomAccessDeniedHandler;
//import org.example.properties.FilterIgnorePropertiesConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.jwt.crypto.sign.RsaVerifier;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//import org.springframework.security.oauth2.provider.token.*;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.access.AccessDeniedHandlerImpl;
//import org.springframework.util.FileCopyUtils;
//
//import java.io.IOException;
//
///**
// * 资源服务器配置
// *
// * @author tangyi
// * @date 2019-03-15 11:37
// */
//@Configuration
//@EnableResourceServer
//public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    private static final String RESOURCE_ID = "resource_id";
//
//    /**
//     * 开放权限的URL
//     */
//    private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;
//
//    private final ObjectMapper objectMapper;
//
//    @Autowired
//    private OAuth2ClientProperties oAuth2ClientProperties;
//
//    @Autowired
//    private ResourceServerProperties resourceServerProperties;
//
//
//    @Autowired
//    public CustomResourceServerConfig(FilterIgnorePropertiesConfig filterIgnorePropertiesConfig,
//                                      ObjectMapper objectMapper) {
//        this.filterIgnorePropertiesConfig = filterIgnorePropertiesConfig;
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(RESOURCE_ID).stateless(false);
//        resources.accessDeniedHandler(accessDeniedHandler());
////        //本地解析
//        resources.tokenServices(defaultTokenServices());
//        resources.tokenStore(jwtTokenStore());
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        String[] ignores = new String[filterIgnorePropertiesConfig.getUrls().size()];
//        http
//                .csrf().disable()
//                .httpBasic().disable()
//                .authorizeRequests()
//                .antMatchers(filterIgnorePropertiesConfig.getUrls().toArray(ignores)).permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
//        //http.oauth2ResourceServer().jwt();
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        Resource resource = new ClassPathResource("public.txt");
//        String publicKey;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        converter.setVerifierKey(publicKey);
//        converter.setVerifier(new RsaVerifier(publicKey));
//        return converter;
//    }
//
////    @Bean
////    public KeyPair keyPair() {
////        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyProperties.getKeyStore().getLocation(), keyProperties.getKeyStore().getPassword().toCharArray());
////        return keyStoreKeyFactory.getKeyPair(keyProperties.getKeyStore().getAlias());
////    }
//
//
//    @Bean
//    public JwtTokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    //
//    @Bean
//    public ResourceServerTokenServices defaultTokenServices() {
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
//        remoteTokenServices.setClientId(oAuth2ClientProperties.getClientId());
//        remoteTokenServices.setClientSecret(oAuth2ClientProperties.getClientSecret());
//        remoteTokenServices.setAccessTokenConverter(jwtAccessTokenConverter());
//        return remoteTokenServices;
//    }
//
//
//    @Bean
//    @ConditionalOnMissingBean(AccessDeniedHandler.class)
//    public AccessDeniedHandler accessDeniedHandler() {
//        return new AccessDeniedHandlerImpl();
//    }
//}

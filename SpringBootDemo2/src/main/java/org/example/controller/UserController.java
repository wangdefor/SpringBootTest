package org.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.example.annotations.UserAuthorization;
import org.example.response.ResponseEntry;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Date 2020/4/14 10:40
 * @Author wangyong
 * @Version 1.0
 **/
@RestController
@ResponseBody
@Slf4j
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;


    @Value("${test.value}")
    private String value;

    @Value("${ms.authentication.service.domain}")
    private String value2;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @Autowired
    private JwtTokenStore jwtTokenStore;

    @GetMapping(value = "/query/user/by/id")
    public ResponseEntry queryById(
            @RequestHeader("Authorization") String tk,
            @RequestParam(value = "userId") Integer userId) {
        OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(tk.split(" ")[1]);
        Map<String, Object> information = oAuth2AccessToken.getAdditionalInformation();
        System.out.println(information);
        log.info(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        log.info(value + useLocalCache + value2);
        return ResponseEntry.ok(userService.queryById(userId));
    }

    public static void main(String[] args) {
        Jwt jwt = JwtHelper.decodeAndVerify("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImJhZWwta2V5LWlkIn0.eyJsb2dpblR5cGUiOiJQV0QiLCJ1c2VyX25hbWUiOiJhZG1pbiIsImNyZWRlbnRpYWxzTm9uRXhwaXJlZCI6dHJ1ZSwic3RhcnQiOjE1ODk3ODUwOTAsInRlbmFudENvZGUiOiJzZGRhIiwibWVzc2FnZSI6IndzZGFkc2Fkc2Fkc2FkIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJlbmFibGVkIjp0cnVlLCJjbGllbnRfaWQiOiJ3ZWJfYXBwIiwic2NvcGUiOlsicmVhZCJdLCJhY2NvdW50Tm9uRXhwaXJlZCI6dHJ1ZSwiaWQiOjEsImV4cCI6MTU4OTg3MTQ5MCwianRpIjoiNTI2NzhmODMtNzc0MS00MzhhLTk5MmMtMTQ3N2NiMGFhYmU0IiwiYWNjb3VudE5vbkxvY2tlZCI6dHJ1ZSwidXNlcm5hbWUiOiJhZG1pbiJ9.JahKeapNbQZaoR1Pwt5mvV14JTYaP7VlsiGg9pK-GilzreJZDpNUTIDiBgUnLbqaCd2B_X13rNzjFQVo-Kur6WS9rV73W5M3ctQnCAoN-jUj2UewUXUcmuzquimj9MbA0ipg0NmrGMAjCrDOLvZuICHyE-VuGsfwYgYbekz7BxaE_L_UmQvYdrwt9PcYdCieZDZKS495hJIyiTlQmNgKT0P7SWyunDKAAA-APM8RCA7lQ1FqFo88CFGEa3wvJ8y19ieqdhBONaMw2-4obvkWzcf2fSPjcfsT-KWtzA_aMT7_gWEEZucZH9xheaQZo2IaJ6xB8KMANI78rta0shYb5g", new RsaVerifier("-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjxSawA+NUNmhB2ctiVnt\n" +
                "YH41WCNoD5STW2iPm5AIsKvm6X67lr6A88qOMRwx9oySdZnUnJ+8L3QJ51fkwuDe\n" +
                "ix5w9yA3f/7LUPWZU8M/7Oi+2nda05JMgU999TUlhTGsp9SPiBqq/iwuqMxU8xKu\n" +
                "F8bpTJTOpzrxH4e5BM6J/UJcOwDZLY6/3zP5w+tbhTvxjc995G4NtUyS4owE1MHe\n" +
                "lj8IJepknjePrE6nXD6ecdL401hstMY838UOOFiCnM8NpiBuNI0nY0qCbb9mhQZ+\n" +
                "7gP3jjM+Ft7R+MFTuEHWQ5UN8qHAPIT9UlLcu9IXdk6YwTsqNavwaTLUcP/ih9HB\n" +
                "6wIDAQABsadsadsdasdasdsad\n" +
                "-----END PUBLIC KEY-----"));
        System.out.println(JSON.toJSONString(jwt));
    }

}

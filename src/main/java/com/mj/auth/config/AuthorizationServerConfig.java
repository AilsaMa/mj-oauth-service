package com.mj.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private TokenStore tokenStore;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("test")//客户端ID
                .authorizedGrantTypes("password", "refresh_token")//设置验证方式
                .scopes("read", "write")
                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))
                .accessTokenValiditySeconds(10000) //token过期时间
                .refreshTokenValiditySeconds(10000); //refresh过期时间
    }

    @Override
    @Primary
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userService); //配置userService 这样每次认证的时候会去检验用户是否锁定，有效等
    }

    @Bean
    public TokenStore tokenStore() {
        //使用内存的tokenStore
       //return new InMemoryTokenStore();
        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
        return redis;
    }
}
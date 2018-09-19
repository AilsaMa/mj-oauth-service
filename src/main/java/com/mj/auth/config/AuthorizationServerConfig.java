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
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**
 * https://www.cnblogs.com/xingxueliao/p/5911292.html
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public TokenStore tokenStore() {
        //使用内存的tokenStore
        //return new InMemoryTokenStore();
        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
        return redis;
    }
    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("test")//客户端ID
                .authorizedGrantTypes("password", "refresh_token")//设置验证方式
                .scopes("read", "write")//用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"))//（需要值得信任的客户端）客户端安全码，如果有的话。
                .accessTokenValiditySeconds(10000) //token过期时间
                .refreshTokenValiditySeconds(10000); //refresh过期时间
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @param endpoints
     * @throws Exception
     */
    @Override
    @Primary
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)//认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个 AuthenticationManager 对象。
                .userDetailsService(userService); //配置userService 这样每次认证的时候会去检验用户是否锁定，有效等
    }


    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
        security.allowFormAuthenticationForClients();//主要是让/oauth/token支持client_id以及client_secret作登录认证
//    	  security
//          .tokenKeyAccess("permitAll()")
//          .checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
//    	  security.passwordEncoder(passwordEncoder);
    }

}
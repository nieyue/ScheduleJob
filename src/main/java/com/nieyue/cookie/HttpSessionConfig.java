package com.nieyue.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionStrategy;

@Configuration  
//@EnableRedisHttpSession   
public class HttpSessionConfig {  
	@Autowired
	CustomerCookieSerializer customerCookieSerializer;
     @Bean  
     public CookieHttpSessionStrategy cookieHttpSessionStrategy() { 
         CookieHttpSessionStrategy strategy = new CookieHttpSessionStrategy();  
         strategy.setCookieSerializer(customerCookieSerializer);  
         return strategy;  
     }  
     
}  
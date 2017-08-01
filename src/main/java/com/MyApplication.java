package com;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nieyue.bean.ScheduleJob;
import com.nieyue.interceptor.TestApiControllerInterceptor;
import com.nieyue.schedule.QuartzEventService;
import com.nieyue.service.ScheduleJobService;
@SpringBootApplication
@Configuration
//@EnableRedisHttpSession
//@Import({DynamicDataSourceRegister.class})
//@EnableJpaRepositories(basePackages = "com.nieyue.dao")
//@EntityScan(basePackages = "com.nieyue.bean")
public class MyApplication extends WebMvcConfigurerAdapter  implements ApplicationListener<ApplicationReadyEvent>{
	@Resource
	TestApiControllerInterceptor testApiControllerInterceptor;
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class,args);
		
	}
	 @Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**").allowedOrigins("*")
	   .allowCredentials(true)
	    .allowedMethods("POST","GET", "OPTIONS", "DELETE").allowedHeaders("x-requested-with");
	  }
	 
	 /** 
	     * 配置拦截器 
	     * @author lance 
	     * @param registry 
	     */  
	    public void addInterceptors(InterceptorRegistry registry) {  
	        registry.addInterceptor(testApiControllerInterceptor).addPathPatterns("/**");  
	    } 
	@Bean
	public EmbeddedServletContainerCustomizer  containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	            ErrorPage error406Page = new ErrorPage(HttpStatus.NOT_ACCEPTABLE, "/404.html");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404.html");
	            
	            container.addErrorPages( error401Page);
	            container.addErrorPages( error404Page);
	            container.addErrorPages( error406Page);
	            container.addErrorPages( error500Page);
	        }
	    };
	}
	@Resource
	ScheduleJobService scheduleJobService;
	@Resource
	QuartzEventService quartzEventService;
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		
		 List<ScheduleJob> l = scheduleJobService.browsePagingScheduleJob(1,Integer.MAX_VALUE,"schedule_job_id","asc");
		 l.forEach(sj -> {
			 quartzEventService.addScheduleJob(sj); 
		 });
	}
		
	}
/*public class MyApplication extends SpringBootServletInitializer {
	@Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(MyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}*/
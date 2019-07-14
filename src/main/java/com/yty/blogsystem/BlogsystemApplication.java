package com.yty.blogsystem;

import com.yty.blogsystem.filter.TestFilter;
import com.yty.blogsystem.logger.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "com.yty.blogsystem",excludeName = {"com.yty.blogsystem.jpa"})
@MapperScan(
        basePackages = "com.yty.blogsystem.*",
        annotationClass = Repository.class
)
public class BlogsystemApplication  extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(BlogsystemApplication.class, args);
        Log.getInst(BlogsystemApplication.class).info("启动 BlogsystemApplication");
    }

    /**
    * @Author  yangtaoyao created in 2019/5/24 0024 0:31
    * 修改RedisTemplate的序列化器
    */
    @Autowired
    private RedisTemplate redisTemplate=null;
    //后初始化方法
    @PostConstruct
    public void init(){
        initRedisTemplate();
    }
    //RedisTemplate的序列化器
    public void initRedisTemplate(){
        RedisSerializer stringSerializer=redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }


    /**
    * @author  yangtaoyao  Created in 2019/5/18 0018 23:47
     * extends WebSecurityConfigurerAdapter
     * 使用数据库定义用户认证服务
    */
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource=null;
    String pwdQuery="select user_name,pwd,available from t_user where user_name = ?";
    String roleQuery="select u.user_name,r.role_name from t_user u,t_user_role ur,t_role r  " +
            "where u.id=ur.user_id and r.id=ur.role_id " +
            "and u.user_name = ?";
    @Value("${system.user.password.secret}")
    private String secret=null;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编码器
        PasswordEncoder passwordEncoder=new Pbkdf2PasswordEncoder(this.secret);
        auth.jdbcAuthentication()
                //密码编码器
                .passwordEncoder(passwordEncoder)
                .dataSource(dataSource)
                //查询用户，自动检测密码是否一致
                .usersByUsernameQuery(pwdQuery)
                //赋予权限
                .authoritiesByUsernameQuery(roleQuery);
    }


    /**
    * @author  yangtaoyao Created in 2019/5/18 0018 19:16
    * 使用后初始化方法，观察自动生成的事务管理器
    */
    @Autowired
    private PlatformTransactionManager platformTransactionManager=null;
    @PostConstruct
    public void viewTransactionManager(){
        Log.getInst(this).info("使用后初始化方法，观察自动生成的事务管理器:"+platformTransactionManager.getClass().getName());
    }

    /**
    * @author  yangtaoyao Created in 2019/5/13 0013 22:33
     * FilterRegistrationBean是springboot提供的，此类提供setOrder方法，
     * 可以为filter设置排序值，让spring在注册web filter之前排序后再依次注册。
     * 注册多个时，就注册多个FilterRegistrationBean即可
    */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //当过滤器有注入其他bean类时，可直接通过@bean的方式进行实体类过滤器，这样不可自动注入过滤器使用的其他bean类。
        //当然，若无其他bean需要获取时，可直接new TestFilter()，也可使用getBean的方式。
        registration.setFilter(testFilter());
        //过滤器名称t
        registration.setName("testFilter");
        //拦截路径
        registration.addUrlPatterns("/*");
        //设置顺序
        registration.setOrder(10);
        return registration;
    }

    @Bean
    public Filter testFilter() {
        return new TestFilter();
    }


}

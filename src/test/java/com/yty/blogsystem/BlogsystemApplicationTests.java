package com.yty.blogsystem;

import com.yty.blogsystem.controller.TestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogsystemApplicationTests {

    @Test
    public void contextLoads() {
    }

    //BCryptPasswordEncoder 加密
    @Test
    public void test89() {
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("uvwxyz");
        // 加密
        String encodedPassword = passwordEncoder.encode("123");
        System.out.println(encodedPassword);
    }

    @Autowired
    private RedisTemplate redisTemplate=null;
    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("key1","123");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

}

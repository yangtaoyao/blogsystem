package com.yty.blogsystem.controller;

import com.yty.blogsystem.logger.Log;
import com.yty.blogsystem.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
* @author  yangtaoyao
* @date  Created in 2019/5/13 0013 18:18
 * @RestController注解，相当于@Controller+@ResponseBody两个注解的结合，
 * 返回json数据不需要在方法前面加@ResponseBody注解了，
 * 但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
*/
@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/test")
    public String TestController() {
        Log.getInst(this).info("============test============");
        return "hello";
    }

    @RequestMapping(value = "/getUserFromMongo")
    public List<User> getUserFromMongo(){
        return mongoTemplate.findAll(User.class);
    }

    @RequestMapping(value = "/insertUserToMongo")
    public String insertUserToMongo(){
        List<User> users=new ArrayList<>();
        User user=null;
        for (int i = 0; i < 10; i++) {
            user=new User();
            user.setName("name"+i);
            user.setPassword(i+"");
            users.add(user);

            mongoTemplate.save(user);
        }
        return "ok";
    }

}

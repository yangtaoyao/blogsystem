package com.yty.blogsystem.controller;

import com.yty.blogsystem.dao.UserDao;
import com.yty.blogsystem.logger.Log;
import com.yty.blogsystem.pojo.User;
import com.yty.blogsystem.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MybatisTestController {
    @Autowired
    private TestUserService testUserService;

    @RequestMapping(value = "/getUser")
    public Map<String, Object> getUser(Long id){
        User user=testUserService.getUser(id);
        if(user==null){
            Log.getInst(this).info("无法找到id为"+id+"的用户");
        }
        Map<String,Object> res=new HashMap<>();
        res.put("code",1);
        res.put("res",user);
        return res;
    }
}

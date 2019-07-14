package com.yty.blogsystem.service;

import com.yty.blogsystem.dao.UserDao;
import com.yty.blogsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestUserService {
    @Autowired
    private UserDao userDao;

    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)//事务
    public User getUser(Long id){
        return userDao.getUserById(id);
    }
}

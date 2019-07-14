package com.yty.blogsystem.dao;

import com.yty.blogsystem.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public User getUserById(Long id);

}

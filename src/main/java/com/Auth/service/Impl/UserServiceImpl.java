package com.Auth.service.Impl;

import com.Auth.dao.UserDAO;
import com.Auth.po.User;
import com.Auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/8/5.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;



    public User login(Map<String, String> map) {
        return userDAO.findUser(map);
    }

    public int modifyUserInfo(Map<String, Object> map) {

        return userDAO.modifyUser(map);
    }

    public List<Map<String,Object>>findAllUser(){
        return  userDAO.findAllUser();
    }
}

package com.Auth.dao;

import com.Auth.po.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/7/23.
 */
public interface UserDAO {

    public User findUser(Map<String, String> map);

    public User findUserById(int userId);

    public int addUser(Map<String, Object> map);

    public int modifyUser(Map<String, Object> map);

    public int removeUserById(int userId);

    public int removeUserRole(int userId);

    public List<Map<String,Object>>findAllUser();

}

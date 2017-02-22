package com.Auth.service;

import com.Auth.po.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/8/5.
 */
public interface UserService {
    public User login(Map<String, String> map);

    public int modifyUserInfo(Map<String, Object> map);

    public List<Map<String,Object>>findAllUser();
}

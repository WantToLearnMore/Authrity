package com.Auth.service.Impl;

import com.Auth.dao.RoleDAO;
import com.Auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/8/25.
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;
    public List<Map<String, Object>> getRoleInfo() {
        return roleDAO.getRoleInfo();
    }
}

package com.Auth.dao;

import com.Auth.po.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/7/23.
 */
public interface RoleDAO {

    public Role findRoleById(int roleId);

    public Role findRoleByName(String roleName);

    public int addRole(Map<String, Object> map);

    //在role_function表中加入信息（将角色与操作绑定）
    public int addRoleAndFunctionRelation(Map<String, Integer> map);

    //在role_user表中加入信息(将用户与角色绑定)
    public int addRoleAndUserRelation(Map<String, Integer> map);

    public int modifyRole(String roleName);

    //删除时 应该把与user和function的相关在关系表中的记录一起删除
    public int removeRoleById(int roleId);

    public int removeFunctionRelation(int roleId);

    public int removeUserRelation(int roleId);



    public  List<Map<String ,Object>>getRoleInfo();


}

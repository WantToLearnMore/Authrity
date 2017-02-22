package com.Auth.service;

import com.Auth.common.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/7/24.
 */
public interface FunctionService {

    //查出 所有的功能 并构造出树形结构
    public List<Node> findAllFunction();

    public   List<Map<String, Object>>getFunctionInfo();

    public Map<String, Object> findFunction(int functionId);

    public int addFunction(Map<String, Object> map);

    public int removeFunction(int functionId);

    public int modifyFunction(Map<String, Object> map);
}

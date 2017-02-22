package com.Auth.service.Impl;

import com.Auth.common.Node;
import com.Auth.common.Tree;
import com.Auth.dao.FunctionDAO;
import com.Auth.po.Function;
import com.Auth.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/7/24.
 */
@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionDAO functionDAO;

    public List<Node> findAllFunction() {
        Tree tree = new Tree();
        return tree.functionTree(functionDAO.findFunctions());
    }

    public List<Map<String, Object>> getFunctionInfo() {
        return functionDAO.findAllFunction();
    }


    public Map<String, Object> findFunction(int functionId) {

        Map<String, Object> map = new HashMap<String, Object>();

        Function function = functionDAO.findFunctionById(functionId);

        map.put(String.valueOf(function), function);
        return map;
    }

    public int addFunction(Map<String, Object> map) {
        return functionDAO.addFunction(map);
    }

    public int removeFunction(int functionId) {

        int temp = functionDAO.removeFunctionRole(functionId);
        int status = functionDAO.removeFunctionById(functionId);

        if (status == 1) {
            status = 0;
        }
        return status;
    }

    public int modifyFunction(Map<String, Object> map) {
        return functionDAO.modifyFunction(map);
    }

}

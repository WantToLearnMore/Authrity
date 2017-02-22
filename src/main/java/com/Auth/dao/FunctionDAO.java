package com.Auth.dao;

import com.Auth.po.Function;

import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/7/23.
 */
public interface FunctionDAO {

    public List<Function> findFunctions();

    public Function findFunctionById(int functionId);

    public int addFunction(Map<String, Object> map);

    public int removeFunctionById(int functionId);

    public int removeFunctionRole(int functionId);

    public int modifyFunction(Map<String, Object> map);


    public   List<Map<String,Object>>findAllFunction();


}

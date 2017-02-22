package com.Auth.common;

import com.Auth.po.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiangtenglong on 2016/7/25.
 */
public class Tree {
    public List<Node> functionTree(List<Function> functions) {
        List<Node> result = new ArrayList<Node>();
        Node node = null;
        for (int i = 0; i < functions.size(); i++) {
            if (functions.get(i).getParentId() == 0) {
                node = new Node();
                node.setId(functions.get(i).getFunctionId());
                node.setParentId(0);
                node.setFunction(functions.get(i));
                System.out.println(functions.get(i).getFunctionName());
                node.setChild(buildChild(functions.get(i), functions));

                result.add(node);
            }
        }
        return result;
    }

    ;

    public List<Node> buildChild(Function function, List<Function> functions) {
        int id = function.getFunctionId();
        List<Node> child = new ArrayList<Node>();
        Node node = null;
        for (int i = 0; i < functions.size(); i++) {
            if (functions.get(i).getParentId() == id) {
                node = new Node();
                node.setId(functions.get(i).getFunctionId());
                node.setParentId(functions.get(i).getFunctionId());
                node.setFunction(functions.get(i));
                node.setChild(buildChild(functions.get(i), functions));
                functions.remove(i);
                child.add(node);
            }
        }
        return child;
    }

}

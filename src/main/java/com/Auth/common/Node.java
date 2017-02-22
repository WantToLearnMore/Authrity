package com.Auth.common;

import com.Auth.po.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiangtenglong on 2016/7/25.
 */
public class Node implements Comparable<Node> {
    private int id;
    private int parentId;
    private Function function;
    private List<Node> child = new ArrayList<Node>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<Node> getChild() {
        return child;
    }

    public void setChild(List<Node> child) {
        this.child = child;
    }

    public int compareTo(Node o) {
        return 0;
    }
}

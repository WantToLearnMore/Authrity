package com.Auth.po;

/**
 * Created by Jiangtenglong on 2016/7/23.
 */
public class Function {
    private int functionId;
    private String functionName;
    private int parentId;
    private String url;
    private int serialNum;
    private int accordion;

    public Function() {
        super();
    }

    public Function(int functionId, String functionName, int parentId, String url, int serialNum, int accordion) {

        super();

        this.functionId = functionId;
        this.functionName = functionName;
        this.parentId = parentId;
        this.url = url;
        this.serialNum = serialNum;
        this.accordion = accordion;

    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getAccordion() {
        return accordion;
    }

    public void setAccordion(int accordion) {
        this.accordion = accordion;
    }
}

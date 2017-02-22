package com.Auth.service;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/8/13.
 */
public interface ExcelService {

    public  void exoprtExcel(List<Map<String, Object>> info, HttpServletResponse response, String[] header);
    public  void importExcel();

    public void exportExcelByTemplate(String filePath, String templatePath);
}

package com.Auth.controller;

import com.Auth.Util.ExcelUtils;
import com.Auth.service.ExcelService;
import com.Auth.service.FunctionService;
import com.Auth.service.RoleService;
import com.Auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by Jiangtenglong on 2016/8/22.
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private UserService userService;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private RoleService roleService;



    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public String user(HttpServletRequest request,
                       HttpServletResponse response,
                       @RequestParam("file_name")String  fileNmae) {

        String xmlPath=request.getSession().getServletContext().getRealPath("template//"+fileNmae+".xml");
        System.out.println(xmlPath);
        String name =fileNmae+".xls";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName="+name);
        String   []  header= ExcelUtils.getAttribute(fileNmae);
        List<Map<String,Object>> info=null;
        if(fileNmae.equals("User")){
            info=userService.findAllUser();
        }else  if(fileNmae.equals("Role")){
            info =roleService.getRoleInfo();
        }else if(fileNmae.equals("Function")) {
            info = functionService.getFunctionInfo();
        }
        excelService.exoprtExcel(info,response,header);

        String status = String.valueOf(1);
        return status;
    }



    @RequestMapping(value = "/ExcelTemlate", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String template(@RequestParam("excel_name")String  excelName,
                           HttpServletRequest request) {
        String p="template//"+excelName+".xml";
        String path=request.getSession().getServletContext().getRealPath(p);
        String filePath=request.getSession().getServletContext().getRealPath("Excel");
        String status = String.valueOf(1);
        return status;
    }
}

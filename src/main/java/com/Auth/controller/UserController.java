package com.Auth.controller;

import com.Auth.annotation.Authrity;
import com.Auth.annotation.AuthrityType;
import com.Auth.common.PrivilegeConfg;
import com.Auth.po.User;
import com.Auth.service.ExcelService;
import com.Auth.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jiangtenglong on 2016/8/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExcelService excelService;

    @Authrity(authrity = AuthrityType.Validate,privilege = PrivilegeConfg.No_limited)
    @RequestMapping(value = "/apply", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String apply(@RequestParam("user_name") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("user_account") String userAccount) {


        Map<String, String> map = new HashMap<String, String>();
        map.put("userName", userName);
        map.put("password", password);
        map.put("userAccount", userAccount);

        String status = String.valueOf(1);


        return status;
    }

    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("user_account") String userAccount,
                        @RequestParam("password") String password) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("password", password);
        map.put("userAccount", userAccount);
        User user = userService.login(map);

        JSONObject jsonObject = new JSONObject();

        if (user != null) {
            jsonObject.put("user_id", user.getUserId());
            jsonObject.put("user_name", user.getUserName());
            jsonObject.put("status", String.valueOf(0));

        } else {
            jsonObject.put("status", String.valueOf(1));
        }
        return jsonObject.toJSONString();
    }

    @Authrity(authrity = AuthrityType.Validate)
    @RequestMapping(value = "/modify", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String userModifyInfo(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session) {

        String userId=session.getAttribute("user_id").toString();
        if(userId==null){
          return  "redirect:html/login.html";
        }
        Map<String,Object>map =new HashMap<String, Object>();
        map.put("userName",request.getParameter("user_name"));
        map.put("password",request.getParameter("password"));
        map.put("usetId",Integer.valueOf(userId));
        int status=1;
        if(userService.modifyUserInfo(map)==1){
            status=0;
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("status",String.valueOf(status));
        return jsonObject.toJSONString();
    }

    //管理员删除用户，并在日子中记录删除记录  参数user_id
    @RequestMapping(value = "/remove", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public String removeUser(@RequestParam("user_id")int userId) {
         String  []attrribute=null;


        List<Map<String,Object>>  info =userService.findAllUser();

            System.out.println(info.get(0).keySet());

        JSONObject jsonObject =new JSONObject();
          jsonObject.put("users",info);
          jsonObject.put("status","0");

        return jsonObject.toJSONString();
    }
}

package com.Auth.aop;

import com.Auth.Util.SesReqRespUtils;
import com.Auth.annotation.Authrity;
import com.Auth.po.UserPrivilege;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by ASUS! on 2017/2/19.
 */
@Aspect
@Service
public class AuthrityCheck {

    /**
     *利用Aop拦截带有Authrity注解的方法
     * 通过验证方法需要的权限和用户的权限判定是否能访问该方法
     * */
    @Around("@annotation(authrity)")
    public Object checkAuthrity(ProceedingJoinPoint joinPoint, Authrity authrity) throws Throwable {
        Object[] args=joinPoint.getArgs();
        for(Object arg:args){
            System.out.println(String.valueOf(arg));
        }
        String isNeedAuthrity=authrity.authrity().toString();
        int privilege=authrity.privilege();
        if(isNeedAuthrity.equals("NoAuthrity")){
            HttpSession session=SesReqRespUtils.getCurrentSession();
            UserPrivilege user= (UserPrivilege) session.getAttribute("user");
            if(user==null){
                return "没有登录";
            }
            return  joinPoint.proceed();
        }else if(isNeedAuthrity.equals("Validate")){
            HttpSession session=  SesReqRespUtils.getCurrentSession();
            UserPrivilege user= (UserPrivilege) session.getAttribute("user");
            if(user==null){
               return "没有登录";
            }else if(privilege>user.getUserPrivilege()){
                return "没有权限";
            }
            System.out.println(isNeedAuthrity+" 权限值是; "+privilege);
           return  joinPoint.proceed();
        }
        else  if(isNeedAuthrity.equals("NoValidate")){
            System.out.println(isNeedAuthrity);
            return  joinPoint.proceed();
        }

       System.out.println(isNeedAuthrity);
        return new String("没有权限进行这项操作"+"没获取到注解");
    }

}

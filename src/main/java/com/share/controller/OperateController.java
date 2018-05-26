package com.share.controller;

import com.share.pojo.Manager;
import com.share.service.IOperateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

;


@Controller
@RequestMapping("/share")
public class OperateController {
    private static Logger logger = Logger.getLogger(OperateController.class);
    @Autowired
    private IOperateService operateService;
    //    @Autowired
//    private IRedisService managerRedisService;
    private Manager manager = new Manager();

    //管理员登录
    @RequestMapping(value = "/login.from", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object managerLogin(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name").trim();
        String pwd = request.getParameter("pwd").trim();
        if (name.equals("") || pwd.equals("")) {
            return "blank";
        } else {
            //redis登录
//            Manager rest = managerRedisService.getManger(name);
//            if (rest != null) {
//                if (rest.getMpassword().equals(pwd)) {
//                    logger.info("redis登录成功");
//                    return "1";
//                } else {
//                    return "0";
//                }
//            } else {
            //mysql登录
            manager.setMname(name);
            manager.setMpassword(pwd);
            Manager res = operateService.login(manager);
//                managerRedisService.addManager(res);
            if (res != null) {
                logger.info("mysql登录成功");
                return "OperateLogin Success";
            } else {
                logger.error("0");
                return "Operate";
            }
        }
//        }
    }

    //管理员注册
    @RequestMapping(value = "/signUp.from", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object managerSignUp(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name").trim();
        String pwd = request.getParameter("pwd").trim();
        String pwd1 = request.getParameter("pwd1").trim();
        manager.setMname(name);
        manager.setMpassword(pwd);
        if (name.equals("") || pwd.equals("")) {
            return "blank";
        } else if (!pwd.equals(pwd1)) {
            return "pwd different";
        } else {
            //验证是否已注册(mysql)
            Manager res = operateService.getManager(manager);
            if (res == null) {
                //向mysql添加数据
                Integer num = operateService.signUp(manager);
                //从mysql获取列id
                Manager res1 = operateService.getManager(manager);
                logger.info(res1.toString());
                //向redis添加数据
//                managerRedisService.addManager(res1);
                if (num == 1) {
                    return "sign up success";
                } else {
                    return "sign up fail";
                }
            } else {
                return "already exist";
            }
        }
    }
}

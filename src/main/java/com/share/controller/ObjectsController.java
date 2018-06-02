package com.share.controller;


import com.share.pojo.Object_1;
import com.share.service.ObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/Objects")
public class ObjectsController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrdersController.class);
    ;

    @Autowired
    private ObjectsService objectsService;
    private Object_1 objects;

    @RequestMapping(value = "/getObjects.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getObjects(HttpServletRequest request, HttpServletResponse response) {
        List<Object_1> objectList = objectsService.getObject();
        String objectCode = "";
        for (int i = 0; i < objectList.size(); i++) {
            objectCode = objectList.get(0).getObjectCode();
            System.out.println(objectCode);
        }
        Cookie name = new Cookie("objectCode", objectCode);
        name.setPath("/");
        name.setMaxAge(60 * 60 * 24);
        response.addCookie(name);

        return objectList;
    }

    @RequestMapping(value = "/updateObjects.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object updateObjects(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        String ObjectCode = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("objectCode".equals(cookie.getName())) {
                    ObjectCode = cookie.getValue();
                }
            }
        }
        String objectCode = request.getParameter("objectCode");
        objectsService.modify(ObjectCode);
        return "modify";
    }

}

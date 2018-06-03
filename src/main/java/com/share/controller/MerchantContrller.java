package com.share.controller;


import com.share.pojo.Merchant;
import com.share.service.IRedisService;
import com.share.service.MerchantService;
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
@RequestMapping("/Merchant")
public class MerchantContrller {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MerchantContrller.class);
    ;

    @Autowired
    private MerchantService merchantService;
    @Autowired
    private IRedisService redisService;
    private Merchant merchant;

    @RequestMapping(value = "/getMerchants.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getMerchants(HttpServletRequest request, HttpServletResponse response) {
        List<Merchant> merchantsList = merchantService.getMerchants();
        String MerchantName = "";
        for (int i = 0; i < merchantsList.size(); i++) {
            MerchantName = merchantsList.get(0).getMerchantName();
//            System.out.println(MerchantName);
        }
        Cookie name = new Cookie("MerchantName", MerchantName);
        name.setPath("/");
        name.setMaxAge(60 * 60 * 24);
        response.addCookie(name);
        return merchantsList;
    }

    @RequestMapping(value = "/updateMerchants.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object updateObjects(HttpServletRequest request, HttpServletResponse response) {
        final Cookie[] cookies = request.getCookies();
        String name = "";
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if ("MerchantName".equals(cookie.getName())) {
                    name = cookie.getValue();
                }
            }
        }
//        String objectCode = request.getParameter("objectCode");

        List<Merchant> merchantsList = merchantService.getMerchants();
//        merchantsList.stream().filter(Merchant::getMerchantName==name);
        for (int i = 0; i < merchantsList.size(); i++) {
            if (merchantsList.get(i).getMerchantName().equals(name)) {
                merchantsList.get(i).setMerchantStatus("1");
                redisService.updateMerchant(merchantsList.get(i));
            }
        }
        merchantService.modify(name);
        return "modify";
    }
}

package com.share.controller;


import com.alibaba.fastjson.JSON;
import com.share.pojo.Orders;
import com.share.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("order")
public class OrdersController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OrdersController.class);
    @Autowired
    private OrdersService ordersService;
    private Orders orders;

    @RequestMapping(value = "/getOrders.form", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getOrdersByUserName(HttpServletRequest request, HttpServletResponse response) {

        List<Orders> ordersList = ordersService.getOrders();
        String res = JSON.toJSONString(ordersList);
        if (ordersList.size() != 0) {
            return res;
        } else {
            return "order is null!";
        }
    }

}

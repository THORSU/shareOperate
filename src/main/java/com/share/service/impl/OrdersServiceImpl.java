package com.share.service.impl;

import com.share.mapper.OrderMapper;
import com.share.pojo.Orders;
import com.share.service.OrdersService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(OrdersServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Orders> getOrders() {
        try {
            List<Orders> ordersList = orderMapper.getOrders();
            return ordersList;
        } catch (Exception e) {
            log.error("getOrderLise.error");
            return null;
        }
    }
}

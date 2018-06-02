package com.share.service.impl;

import com.share.mapper.ObjectsMapper;
import com.share.pojo.Object_1;
import com.share.service.ObjectsService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ObjectsServiceImpl implements ObjectsService {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ObjectsService.class);
    @Autowired
    private ObjectsMapper objectsMapper;

    @Override
    public List<Object_1> getObject() {
        try {
            List<Object_1> ordersList = objectsMapper.getObject();
            return ordersList;
        } catch (Exception e) {
            log.error("getOrderList.error");
            return null;
        }
    }

    @Override
    public int modify(String objectCode) {
        return objectsMapper.modify(objectCode);
    }
}

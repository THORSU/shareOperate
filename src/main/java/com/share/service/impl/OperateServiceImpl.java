package com.share.service.impl;

import com.share.mapper.OperateMapper;
import com.share.pojo.Manager;
import com.share.service.IOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OperateServiceImpl implements IOperateService {
    @Autowired
    private OperateMapper operateMapper;

    @Override
    public Manager login(Manager manager) {
        return operateMapper.login(manager);
    }

    @Override
    public Integer signUp(Manager manager) {
        return operateMapper.signUp(manager);
    }

    @Override
    public Manager getManager(Manager manager) {
        return operateMapper.getManager(manager);
    }
}

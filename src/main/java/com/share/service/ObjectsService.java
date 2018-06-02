package com.share.service;

import com.share.pojo.Object_1;

import java.util.List;

public interface ObjectsService {

    List<Object_1> getObject();

    public int modify(String code);
}

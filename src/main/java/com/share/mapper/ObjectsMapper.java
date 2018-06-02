package com.share.mapper;

import com.share.pojo.Object_1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ObjectsMapper {
    List<Object_1> getObject();

    public int modify(@Param("objectCode") String objectCode);
}

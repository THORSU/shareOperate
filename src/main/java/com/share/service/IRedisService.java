package com.share.service;

import com.share.pojo.Manager;
import com.share.pojo.Merchant;
import com.share.pojo.Object_1;

public interface IRedisService {
    /**
     * 增加管理员
     *
     * @param manager
     */
    void addManager(Manager manager);

    /**
     * 获取redis的信息
     *
     * @param username
     * @return
     */
    Manager getManger(String username);

    /**
     * redis增加商家
     *
     * @param merchant
     */
    void addMerchant(Merchant merchant);

    /**
     * redis获取商家
     *
     * @param merchantName
     * @return
     */
    Merchant getMerchant(String merchantName);

    /**
     * 添加商品
     *
     * @param object_1
     */
    void addObject(Object_1 object_1);
}

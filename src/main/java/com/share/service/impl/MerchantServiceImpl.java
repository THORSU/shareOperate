package com.share.service.impl;

import com.share.mapper.MerchantMapper;
import com.share.pojo.Merchant;
import com.share.service.MerchantService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MerchantService.class);
    @Autowired
    private MerchantMapper merchantMapper;

    public List<Merchant> getMerchants() {
        try {
            List<Merchant> merchantssList = merchantMapper.getMerchants();
            return merchantssList;
        } catch (Exception e) {
            log.error("getOrderList.error");
            return null;
        }
    }

    @Override
    public int modify(String MerchantName) {
        return merchantMapper.modify(MerchantName);
    }
}

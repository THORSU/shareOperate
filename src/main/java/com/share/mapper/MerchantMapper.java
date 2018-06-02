package com.share.mapper;

import com.share.pojo.Merchant;

import java.util.List;

public interface MerchantMapper {
    List<Merchant> getMerchants();

    public int modify(String MerchantName);
}

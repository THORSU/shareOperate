package com.share.service;

import com.share.pojo.Merchant;

import java.util.List;

public interface MerchantService {

    List<Merchant> getMerchants();

    public int modify(String MerchantName);
}

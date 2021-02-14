package com.zhao.service;

import com.zhao.pojo.Merchant;

import java.util.List;

public interface MerchantService {
    List<Merchant> selectAll();

    Merchant selectById(int id);
}

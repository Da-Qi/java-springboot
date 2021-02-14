package com.zhao.service.Impl;

import com.zhao.mapper.MerchantMapper;
import com.zhao.pojo.Merchant;
import com.zhao.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    public MerchantMapper merchantMapper;

    @Override
    public List<Merchant> selectAll() {
        return merchantMapper.selectAll();
    }

    @Override
    public Merchant selectById(int id) {
        return merchantMapper.selectById(id);
    }
}

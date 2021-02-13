package com.zhao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mapper.LimoMapper;
import com.zhao.pojo.Limo;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.service.LimoService;
import com.zhao.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimoServiceImpl implements LimoService {
    @Autowired
    private LimoMapper limoMapper;
    @Override
    public List selectLimo(String type,PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Limo> limos = limoMapper.selectByType(type);
        PageResult pageResult = PageUtils.getPageResult(new PageInfo<>(limos));
        return pageResult.getContent();
    }

    @Override
    public List<Limo> getLimoPopularity() {
        return limoMapper.getLimoPopularity();
    }

    @Override
    public List<Limo> getLimoNewest() {
        return limoMapper.getLimoNewest();
    }

    @Override
    public List<Limo> getLimoTheme() {
        return limoMapper.getLimoTheme();
    }
}

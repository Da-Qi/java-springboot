package com.zhao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mapper.FavoriteMapper;
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
    @Autowired
    private FavoriteMapper favoriteMapper;
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

    @Override
    public Limo getLimoDetails(int id) {
        return limoMapper.getLimoDetails(id);
    }

    @Override
    public boolean ifLimoFavorite(int limo_id, int user_id) {
        String limos = favoriteMapper.getLimos(user_id);
        if (limos == null){
            return false;
        }else {
            return limos.contains(String.valueOf(limo_id));
        }
    }

    //添加收藏
    @Override
    public void addFavorite(int id, int user_id) {
        String limos = favoriteMapper.getLimos(user_id);
        if (limos == null){
            //该用户还没有开始收藏房车
            limos = String.valueOf(id);
            favoriteMapper.insertLimos(limos,user_id);
        }else {
            //已经收藏了用户
            limos += ";" + id;
            favoriteMapper.updateLimos(limos, user_id);
        }
    }
}

package com.zhao.service;

import com.zhao.pojo.Limo;
import com.zhao.pojo.PageRequest;

import java.util.List;

public interface LimoService {
    List<Limo> selectLimo(String type,PageRequest pageRequest);

    List<Limo> getLimoPopularity();

    List<Limo> getLimoNewest();

    List<Limo> getLimoTheme();
}

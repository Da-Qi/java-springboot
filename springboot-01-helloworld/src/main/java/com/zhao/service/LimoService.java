package com.zhao.service;

import com.zhao.pojo.Limo;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;

import java.util.List;

public interface LimoService {
    PageResult selectLimo(String type, PageRequest pageRequest);

    List<Limo> getLimoPopularity();

    List<Limo> getLimoNewest();

    List<Limo> getLimoTheme();

    Limo getLimoDetails(int id);

    boolean ifLimoFavorite(int limo_id, int user_id);

    void addFavorite(int id, int user_id);

    void removeFavorite(int id, int user_id);

    List<Limo> getLimoRecommend();
}

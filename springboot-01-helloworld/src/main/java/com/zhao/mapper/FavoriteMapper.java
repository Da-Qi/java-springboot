package com.zhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface FavoriteMapper {
    String getLimos(int user_id);

    void updateLimos(String limos, int user_id);

    void insertLimos(String limos, int user_id);

    String getUserRouteFavorite(int user_id);

    int ifUserExists(int user_id);

    void insertRoutes(String routes, int user_id);

    void updateRoutes(String routes, int user_id);
}

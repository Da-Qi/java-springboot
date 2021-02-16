package com.zhao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface FavoriteMapper {
    String getLimos(int user_id);

    void updateLimos(String limos, int user_id);

    void insertLimos(String limos, int user_id);
}

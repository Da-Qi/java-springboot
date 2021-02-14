package com.zhao.mapper;

import com.zhao.pojo.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MerchantMapper {
    List<Merchant> selectAll();

    Merchant selectById(int id);
}

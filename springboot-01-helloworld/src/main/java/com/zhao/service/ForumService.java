package com.zhao.service;

import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;

import java.util.List;

public interface ForumService {

    PageResult selectPostByPageQuery(PageRequest pageRequest);

    PageResult selectPostByCategory(PageRequest pageRequest, String searchKey);

    List<String> selectTopTenCategory();
}

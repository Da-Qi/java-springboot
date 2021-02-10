package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    //当前页码
    private int pageNum;
    //每页数量
    private int pageSize;
    //记录总数
    private long totalSize;
    //页码总数
    private int totalPages;
    //数据模型
    private List<T> content;
}

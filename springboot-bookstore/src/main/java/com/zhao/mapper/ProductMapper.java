package com.zhao.mapper;


import com.zhao.pojo.Order;
import com.zhao.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    /**
     * 查询该种类的总数
     */
    int count(@Nullable String category);

    /**
     * 查询所有商品总数
     */
    //int count();

    /**
     * 根据种类和页面查询书籍；
     */
    List<Product> findBooksByPage(@Nullable String category);

    /**
     * 查询所有书籍
     */
    //List<Product> findBooks(int currentpage,int pageSize);

    /**
     * 根据id查找书籍
     */
    Product findBookById(String id);

    /**
     * 更新库存
     */
    void updatePNum(Order order) throws SQLException;

    /**
     * 根据书名查找包含它的数量
     */
    int countByName(String bookname);

    /**
     * 根据书名搜索结果，传出某一页的书结果
     */
    List<Product> findBooksBySearch(String bookname);

    /**
     * 更新书籍信息
     */
    void updateProduct(Product product);

    /**
     * 删除书籍
     */
    void deleteProductById(@Param("id") String productId);

    /**
     *
     * @param category
     * @param name
     * @param minprice
     * @param maxprice
     * @return
     */
    List<Product> findProductByConditions(@Nullable String category,@Nullable String name,@Nullable String minprice,@Nullable String maxprice);

    /**
     * 添加商品
     */
    void addProduct(Product product);
}

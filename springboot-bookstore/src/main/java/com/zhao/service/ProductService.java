package com.zhao.service;

import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    PageResult findBooksByCategory(String category, PageRequest pageRequest);

    Product findBookById(String id);

    PageResult<Product> findBooksBySearch(String bookname, PageRequest pageRequest);

    void modifyProduct(Product product);

    void deleteProductById(String productId);

    PageResult<Product> findProductByConditions(String category, String name, String minprice, String maxprice,PageRequest pageRequest);

    void addProduct(Product product);
}

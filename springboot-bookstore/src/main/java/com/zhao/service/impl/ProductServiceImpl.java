package com.zhao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mapper.ProductMapper;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.Product;
import com.zhao.pojo.User;
import com.zhao.service.ProductService;
import com.zhao.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    /**
     * 根据类别查询书籍，如果类目为空，就查询所有
     * @param category
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult<Product> findBooksByCategory(String category, PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.findBooksByPage(category);
        return PageUtils.getPageResult(new PageInfo<Product>(products));
    }

    public Product findBookById(String id) {
        return productMapper.findBookById(id);
    }

    public PageResult<Product> findBooksBySearch(String bookname, PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.findBooksBySearch(bookname);
        return PageUtils.getPageResult(new PageInfo<>(products));
    }

    @Override
    public void modifyProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteProductById(String productId) {
        productMapper.deleteProductById(productId);
    }

    @Override
    public PageResult<Product> findProductByConditions(String category, String name, String minprice, String maxprice,PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.findProductByConditions(category, name, minprice, maxprice);
        return PageUtils.getPageResult(new PageInfo<>(products));
    }

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

}

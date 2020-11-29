package com.zhao.mapper;

import com.zhao.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    List<Book> queryAllBooks();
    Book queryById(int id);
    int addBook(Book book);
    int updateBook(Book book);
    int deleteBook(int id);
}

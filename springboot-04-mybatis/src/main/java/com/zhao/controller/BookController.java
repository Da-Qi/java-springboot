package com.zhao.controller;

import com.zhao.mapper.BookMapper;
import com.zhao.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping("/queryAll")
    public List<Book> queryAllBooks(){
        List<Book> books = bookMapper.queryAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
        return books;
    }
}

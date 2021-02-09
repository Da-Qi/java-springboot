package com.zhao.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user!=null){
            //登录,放行
            return true;
        }else {
            //未登录
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }
    }
}

package com.yty.blogsystem.interceptor;

import com.yty.blogsystem.logger.Log;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author  yangtaoyao
* @date  Created in 2019/5/13 0013 22:41
* @description
 * spring 拦截器测试
*/
public class TestHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Log.getInst(this).info("拦截器TestHandlerInterceptor preHandle:请求前调用");
        //返回 false 则请求中断
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Log.getInst(this).info("拦截器TestHandlerInterceptor postHandle:请求后调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Log.getInst(this).info("拦截器TestHandlerInterceptor afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
    }
}

package com.fruitsalesplatform.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dpcraft
 * @date 2018-12-29
 * @time 19:33
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String uri = request.getRequestURI();

        if(!(uri.contains("Login") || uri.contains("login") || uri.contains("register"))) {
            if(request.getSession().getAttribute("user") != null) {
                return true;
            }else{
                if(uri.contains("css") || uri.contains("js") || uri.contains("images")){
                    return true;
                }else {
                    response.sendRedirect(request.getContextPath() + "/user/toLogin.action");
                }
            }
        }else {
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception{

    }


}

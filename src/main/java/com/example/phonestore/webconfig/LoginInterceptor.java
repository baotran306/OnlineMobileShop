package com.example.phonestore.webconfig;

import com.example.phonestore.object.user.ResponseLoginMessage;
import com.example.phonestore.object.user.StaffInfo;
import com.example.phonestore.object.user.User;
import com.example.phonestore.service.LoginService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        ResponseLoginMessage responseLoginMessage = (ResponseLoginMessage) session.getAttribute("user");
        if (responseLoginMessage == null) {
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }
        if (responseLoginMessage != null) {
            if (responseLoginMessage.getResult() == false) {
                response.sendRedirect(request.getContextPath()+"/admin/login");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.example.phonestore.webconfig;

import com.example.phonestore.object.user.ResponseLoginMessage;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StaffInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        ResponseLoginMessage responseLoginMessage = (ResponseLoginMessage) session.getAttribute("user");
        if (responseLoginMessage == null) {
            response.sendRedirect(request.getContextPath()+"/admin/order");
            return false;
        }
        if (responseLoginMessage != null) {
            if (responseLoginMessage.getStaffInfo().getRoleId() != 1) {
                response.sendRedirect(request.getContextPath()+"/admin/order");
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

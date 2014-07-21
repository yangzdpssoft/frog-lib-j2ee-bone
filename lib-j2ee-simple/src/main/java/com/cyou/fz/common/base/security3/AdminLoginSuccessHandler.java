package com.cyou.fz.common.base.security3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Description: 登陆成功后处理逻辑.</p>
 * <p>Company: cyou</p>
 *
 * @author yangz
 * @version V1.0
 * @date 2013-7-8
 */
public class AdminLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired(required = false)
    private IGetAdmin adminService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	request.getSession().setAttribute("nickName", adminService.getAdmin(user.getUsername()).getNickName());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

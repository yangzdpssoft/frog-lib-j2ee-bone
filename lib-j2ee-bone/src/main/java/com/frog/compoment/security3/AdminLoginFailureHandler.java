package com.frog.compoment.security3;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败后业务逻辑.
 *
 * @author yangz
 * @date 2013-4-8 下午5:34:53
 */
public class AdminLoginFailureHandler extends ExceptionMappingAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        request.setAttribute("LOGIN_ERROR_MESSAGE", "用户名密码错误.");
        super.onAuthenticationFailure(request, response, exception);
    }


}

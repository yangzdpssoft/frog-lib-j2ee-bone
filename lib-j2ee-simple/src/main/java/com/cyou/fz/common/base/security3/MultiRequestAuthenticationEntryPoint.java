package com.cyou.fz.common.base.security3;

import com.cyou.fz.common.base.springmvc.ajax.Response;
import com.cyou.fz.common.base.util.IOUtil;
import com.cyou.fz.common.base.util.JsonUtil;
import com.cyou.fz.common.base.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * security认证入口点,如果认证失败将执行commence,
 * MultiRequestAuthenticationEntryPoint支持ajax.
 * @Company : cyou
 * @author yangz
 * @date   2012-11-1 上午01:41:09
 */
@SuppressWarnings("deprecation")
public class MultiRequestAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private static final Log logger = LogFactory.getLog(MultiRequestAuthenticationEntryPoint.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String redirectUrl = null;
        String url = request.getRequestURI();
        if (logger.isDebugEnabled()) {
            logger.debug("url:" + url);
        }
        if (!WebUtil.isAjaxRequest(request)) {// 非ajax请求
            if (this.isUseForward()) {
                if (this.isForceHttps() && "http".equals(request.getScheme())) {
                    // First redirect the current request to HTTPS.
                    // When that request is received, the forward to the login page will be used.
                    redirectUrl = buildHttpsRedirectUrlForRequest(request);
                }
                if (redirectUrl == null) {
                    String loginForm = determineUrlToUseForThisRequest(request, response, authException);

                    if (logger.isDebugEnabled()) {
                        logger.debug("Server side forward to: " + loginForm);
                    }
                    RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);
                    dispatcher.forward(request, response);
                    return;
                }
            } else {
                // redirect to login page. Use https if forceHttps true
                redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);
            }
            redirectStrategy.sendRedirect(request, response, redirectUrl);
        } else {//ajax请求
            Response<Boolean> responseData = new Response<Boolean>();
            responseData.setResult(Response.RESULT_LOGIN); //访问被拒绝,请求登录.
            responseData.setMessage("未登录");
            PrintWriter writer = response.getWriter();
            try {
                writer.write(JsonUtil.toJson(responseData));
            } finally {
                IOUtil.close(writer);
            }
        }
    }

}
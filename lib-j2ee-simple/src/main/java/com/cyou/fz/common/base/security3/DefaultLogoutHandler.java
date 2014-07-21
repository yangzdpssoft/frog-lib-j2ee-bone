package com.cyou.fz.common.base.security3;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销默认的实现.
 * 
 * @author yangz
 * @date 2013-4-8 下午5:11:44
 */
public class DefaultLogoutHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		 super.handle(request, response, authentication);
	}

}

package com.cyou.fz.common.base.security3;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 管理员接口.
 * @author yangz
 * @date 2014/7/14
 *
 */

public interface Admin extends UserDetails{
    /**
     * 昵称.
     * @return
     */
    public String getNickName();

    /**
     * 角色.
     * @return
     */
    public List<String> getRoles();
}

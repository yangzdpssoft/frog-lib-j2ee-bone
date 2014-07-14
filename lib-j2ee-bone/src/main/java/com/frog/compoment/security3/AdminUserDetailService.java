package com.frog.compoment.security3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Description: 获取登陆用户信息.</p>
 * <p>Company: cyou</p>
 * @author yangz
 * @date 2013-7-8
 * @version V1.0
 */
public class AdminUserDetailService implements UserDetailsService {

    @Autowired(required = false)
    private IGetAdmin adminService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdmin(username);
        List<String> roles = admin.getRoles();
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        if(roles != null){
            for (String r : roles){
                GrantedAuthority auth = new SimpleGrantedAuthority(r);
                auths.add(auth);
            }
        }
        GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_LOGIN");
        auths.add(auth);
        return new User(admin.getUsername(), admin.getPassword(), true, true, true, true, auths);
    }
}

package com.frog.dao;


import com.frog.bean.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UsersDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(Users users) {
        Serializable serializable = this.getCurrentSession().save(users);
        return serializable;
    }
}

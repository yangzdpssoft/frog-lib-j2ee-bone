package com.frog.dao;


import com.frog.bean.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath*:applicationContent-*.xml" })
public class UsersDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UsersDAO usersDAO;

    @Test
    public void testSave() {
        Users users = new Users();
        users.setName("yangz2");
        usersDAO.save(users);

    }
}

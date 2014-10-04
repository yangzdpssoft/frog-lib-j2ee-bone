package com.frog.dao;


import com.frog.bean.MongoBean;
import com.frog.bean.SubBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath*:applicationContent-*.xml" })
public class MDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MDAO mdao;

    @Autowired
    private SubDAO subDAO;
    @Test
    public void testSave(){
        MongoBean bean = new MongoBean();
        bean.setName("ooo");
        SubBean sub1 = new SubBean();
        sub1.setId("542fc0a46de36b927c5d18c9");
        SubBean sub2 = new SubBean();
        sub2.setId("542fc0a46de36b927c5d18ca");
        bean.getSubBeans().add(sub1);
        bean.getSubBeans().add(sub2);
        mdao.saveOrUpdate(bean);
//        SubBean sub1 = new SubBean();
//        sub1.setSubName("您们好");
//        subDAO.saveOrUpdate(sub1);
//        SubBean sub2 = new SubBean();
//        sub2.setSubName("搭建好");
//        subDAO.saveOrUpdate(sub2);
    }

    @Test
    public void testInc(){

//        SubBean sub1 = new SubBean();
//        sub1.setSubName("您们好");
//        subDAO.saveOrUpdate(sub1);
//        SubBean sub2 = new SubBean();
//        sub2.setSubName("搭建好");
//        subDAO.saveOrUpdate(sub2);
    }

    @Test
    public void testDelete(){
        mdao.delete(new String[]{"542fc43c6de3db7d518e83ca"});
    }
    @Test
    public void testString(){
        String[] s = "a".split("_");
        for (String ss : s){
            System.out.println(ss);
        }
    }
}

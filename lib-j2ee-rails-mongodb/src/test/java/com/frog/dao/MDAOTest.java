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
        sub1.setId("53d213bf441eee6cdaa0bac1");
        SubBean sub2 = new SubBean();
        sub2.setId("53d213c0441eee6cdaa0bac2");
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

        mdao.inc("53d2194a441e5f83eeb4adee", "inc", 1);
//        SubBean sub1 = new SubBean();
//        sub1.setSubName("您们好");
//        subDAO.saveOrUpdate(sub1);
//        SubBean sub2 = new SubBean();
//        sub2.setSubName("搭建好");
//        subDAO.saveOrUpdate(sub2);
    }

    @Test
    public void testDelete(){
        mdao.delete(new String[]{"53d21526441ed16efa4dc877"});
    }
    @Test
    public void testString(){
        String[] s = "a".split("_");
        for (String ss : s){
            System.out.println(ss);
        }
    }
}

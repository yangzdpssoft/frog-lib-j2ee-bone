package com.frog.dao;


import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Arrays;

@ContextConfiguration(locations = { "classpath*:applicationContent-*.xml" })
public class MDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MDAO mdao;

    @Autowired
    private SubDAO subDAO;
    @Test
    public void testSave(){
//        MongoBean bean = new MongoBean();
//        bean.setName("ooo");
//        SubBean sub1 = new SubBean();
//        sub1.setId("542fc0a46de36b927c5d18c9");
//        SubBean sub2 = new SubBean();
//        sub2.setId("542fc0a46de36b927c5d18ca");
//        bean.getSubBeans().add(sub1);
//        bean.getSubBeans().add(sub2);
//        mdao.saveOrUpdate(bean);
//        SubBean sub1 = new SubBean();
//        sub1.setSubName("您们好");
//        subDAO.saveOrUpdate(sub1);
//        SubBean sub2 = new SubBean();
//        sub2.setSubName("搭建好");
//        subDAO.saveOrUpdate(sub2);
        try {
            /*****1. 填写数据库相关信息(请查找数据库详情页)*****/
            String databaseName = "LsnOiJLHaEErUwhLxzPX";
            String host = "mongo.duapp.com";
            String port = "8908";
            String username = "VzGBaH6BBNVwVMPAVwveOvjP";//用户名(api key);
            String password = "avMWzOhivTCyoz2mkZpePz8av5D9Vvjn";//密码(secret key)
            String serverName = host + ":" + port;

            /******2. 接着连接并选择数据库名为databaseName的服务器******/
            MongoClient mongoClient = new MongoClient(new ServerAddress(serverName), Arrays.asList(MongoCredential.createMongoCRCredential(username, databaseName, password.toCharArray())),						new MongoClientOptions.Builder().cursorFinalizerEnabled(false).build());
            DB mongoDB = mongoClient.getDB(databaseName);
            mongoDB.authenticate(username, password.toCharArray());
            /*至此连接已完全建立，就可对当前数据库进行相应的操作了*/
            /**
             * 3. 接下来就可以使用mongo数据库语句进行数据库操作,详细操作方法请参考java-mongodb官方文档
             */

            //集合并不需要预先创建
            DBCollection mongoCollection = mongoDB.getCollection("test_mongo");

            //插入数据
            DBObject data1 = new BasicDBObject();
            data1.put("no", 2007);
            data1.put("name", "this is a test message");
            mongoCollection.insert(data1);
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

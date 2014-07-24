package com.frog.dao;


import com.cyou.fz.common.base.db.mongo.util.MongoUtil;
import com.frog.bean.MongoBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@ContextConfiguration(locations = { "classpath:applicationContent-*.xml" })
public class MDAOTest extends AbstractTestNGSpringContextTests {

    @Test
    public void testQuery(){
        Map<String, Object> cond = new HashMap<>();
        cond.put("cond!a_b_id", 1);
        cond.put("cond!a", 1);
        MongoUtil.conventCondQueryBuilder(cond, MongoBean.class);
    }

    @Test
    public void testString(){
        String[] s = "a".split("_");
        for (String ss : s){
            System.out.println(ss);
        }
    }
}

package com.lzj.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

/**
 * Created by li on 17-7-16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogDaoTest {
    @Autowired
    private BlogDao blogDao;

    @Test
    public void findAllTest(){
       System.out.println(blogDao.findAllBlog(1));
       System.out.println(blogDao.findAllByNameAndId(1,"c"));
    }
}

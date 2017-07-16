package com.lzj.dao;

import com.lzj.domain.Blog;
import com.lzj.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by li on 17-7-16.
 */
public interface BlogDao extends CrudRepository<Blog,Integer>{
    @Query("select b from Blog b inner join b.user u on u.id=b.user where u.id=:id")
    public List<Blog> findAllBlog(@Param("id") Integer id);
    @Query("select b from Blog b inner join b.user u on u.id=b.user where u.id=?1 and b.content=?2")
    public List<Blog> findAllByNameAndId(Integer id,String content);
}

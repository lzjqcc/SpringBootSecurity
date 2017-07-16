package com.lzj.dao;

import com.lzj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by li on 17-7-16.
 */

public interface UserDao extends CrudRepository<User,Integer> {
}

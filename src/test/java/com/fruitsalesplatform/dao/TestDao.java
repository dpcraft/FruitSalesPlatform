package com.fruitsalesplatform.dao;

import com.fruitsalesplatform.entity.User;

import java.util.List;

/**
 * @author dpcraft
 * @date 2018-12-29
 * @time 19:47
 */
public interface TestDao {
    public List<User> findUserByName(User user);
}

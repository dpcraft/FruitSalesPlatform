package com.fruitsalesplatform.mapper;

import com.fruitsalesplatform.entity.User;

import java.util.List;

/**
 * @author dpcraft
 * @date 2018-12-29
 * @time 20:22
 */
public interface UserMapper {
    List<User> findUserByName(String name);
}

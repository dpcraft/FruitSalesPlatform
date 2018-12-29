package com.fruitsalesplatform.service;

import com.fruitsalesplatform.entity.User;

import java.util.List;

/**
 * @author dpcraft
 * @date 2018-12-29
 * @time 21:45
 */
public interface TestService {
    List<User> findUserByName(User user);
}

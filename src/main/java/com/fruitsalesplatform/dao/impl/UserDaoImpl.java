package com.fruitsalesplatform.dao.impl;

import com.fruitsalesplatform.dao.UserDao;
import com.fruitsalesplatform.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 15:57
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    public UserDaoImpl() {
        super.setNs("com.fruitsalesplatform.mapper.UserMapper");
    }
}

package com.fruitsalesplatform.service.impl;

import com.fruitsalesplatform.dao.TestDao;
import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dpcraft
 * @date 2018-12-29
 * @time 21:47
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Override
    public List<User> findUserByName(User user){
        return testDao.findUserByName(user);
    }
}

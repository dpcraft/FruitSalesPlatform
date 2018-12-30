package com.fruitsalesplatform.service;

import com.fruitsalesplatform.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 16:06
 */
public interface UserService {
    /**
     * 查询一个数据
     * @param id
     * @return
     */
    User get(Serializable id);

    List<User> find(Map map);

    void insert(User user);

    void update(User user);
    void deleteById(Serializable id);
    void delete(Serializable[] ids);
}

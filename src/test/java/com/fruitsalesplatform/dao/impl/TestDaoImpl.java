package com.fruitsalesplatform.dao.impl;

import com.fruitsalesplatform.dao.TestDao;
import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dpcraft
 * @date 2018-12-29
 * @time 19:47
 */
@Repository
public class TestDaoImpl implements TestDao {
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//    private SqlSession sqlSession = null;
//
//    private SqlSession getSqlSession() {
//        if(sqlSession == null) {
//            sqlSession = sqlSessionFactory.openSession();
//        }
//        return sqlSession;
//    }
//public List<User> findUserByName(User user) {
//    List<User> uList = getSqlSession().selectList("test.findUserByName", "%" + user.getName() + "%");
//    return uList;
//}
    @Autowired
    private UserMapper userMapper;

    public List<User> findUserByName(User user) {
        List<User> uList = userMapper.findUserByName(user.getName());
        return uList;
    }
}

package com.fruitsalesplatform.db;

import com.fruitsalesplatform.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

/**
 * @author dpcraft
 * @date 2018-12-29
 * @time 19:56
 */
public class DbConnectionTest {
//    private static String resource = "bean.xml";
//    private static SqlSessionFactory sqlSessionFactory;
//    private SqlSession sqlSession = null;
//    @BeforeClass
//    public static void init() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(resource);
//        sqlSessionFactory = (SqlSessionFactory) context.getBean("sessionFactory");
//    }
//    @Test
//    public void testConnection() throws Exception{
//        sqlSession = getSqlSession();
//        assertNotNull(sqlSession);
//
//    }
//
//    @Test
//    public void TestSelect() throws Exception{
//        sqlSession = getSqlSession();
//        User user = sqlSession.selectOne("test.findUserByName", "张三");
//        System.out.println("取出的信息");
//        System.out.println(user);
//    }
//
//    public SqlSession getSqlSession() {
//        if(sqlSession == null) {
//            sqlSession = sqlSessionFactory.openSession();
//        }
//        return sqlSession;
//    }

    @Test
    public void TestS() throws Exception{
        String resource = "bean.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(resource);
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sessionFactory");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("com.fruitsalesplatform.mapper.UserMapper.findUserByName", "张三");
        System.out.println("取出的信息");
        System.out.println(user);
    }
    @Test
    public void TestLog() {
        Logger logger = LoggerFactory.getLogger("xxx");
        logger.debug("________________________testLog");
    }
}

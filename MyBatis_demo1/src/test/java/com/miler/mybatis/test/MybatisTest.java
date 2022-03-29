package com.miler.mybatis.test;

import com.miler.mybatis.mapper.UserMapper;
import com.miler.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author milerliu
 * @Date 2022/3/29 17:08
 * @Version 1.0
 */
public class MybatisTest {
    /**
     * sqlSession不自动提交事务，如果想要自动提交，可以在openSession时传参true
     * @throws IOException
     */
    @Test
    public void testInsert() throws IOException {
        // 加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 获取mapper接口对象 底层使用代理模式
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 执行插入方法
        int effectLine = userMapper.insertUser();
        System.out.println("Insert User: "  + effectLine);

        // 提交事务
        sqlSession.commit();

        // 关闭session
        sqlSession.close();
    }

    /**
     * 测试更新
     * @throws IOException
     */
    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updateUser();
    }

    @Test
    public void testDelete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUser();
    }

    @Test
    public void testSelectById() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.getUserById();
        System.out.println(user);
    }

    @Test
    public void testSelectAll() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> allUser = mapper.getAllUser();
        allUser.forEach(System.out::println);
    }
}

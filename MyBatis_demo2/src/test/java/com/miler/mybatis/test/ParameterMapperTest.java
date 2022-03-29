package com.miler.mybatis.test;

import com.miler.mybatis.mapper.ParameterMapper;
import com.miler.mybatis.pojo.User;
import com.miler.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author milerliu
 * @Date 2022/3/29 23:04
 * @Version 1.0
 */
public class ParameterMapperTest {
    /**
     * ${} 字符串拼接
     * #{} 占位符
     */
    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(System.out::println);
    }

    /**
     * 1,mapper接口方法的参数为单个的字面量类型
     * 可以通过${}和#{}以任意的名称获取参数值，但是需要注意$(}的单引号问题
     */
    @Test
    public void testGetUserByName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.getUserByUsername("王冉");
        System.out.println(user);
    }

    /**
     * 2,mapper接口方法的参数为多个时
     * 此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
     * a>以argB,arg1...为键，以参数为值
     * b>以param1,param2...为键，以参数为值
     * 因此只需要通过#{}和${}以键的方式访问值即可，便是需要注意${}的单引号问题
     */
    @Test
    public void testCheckLogin() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("王冉", "123456");
        System.out.println(user);
    }

    /**
     * 3. 若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中存储
     * 因此只需要通过#{}和${}以键的方式访问值即可，便是需要注意${}的单引号问题
     */
    @Test
    public void testCheckLoginByName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "王冉");
        map.put("password", "123456");
        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }

    /**
     * 4,mapper接口方法的参数是实体类类型的参数
     * 因此只需要通过#{}和${}以属性的方式访问值即可，便是需要注意${}的单引号问题
     */
    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        int effectLine = mapper.insertUser(new User(null, "里斯", "123", 32, "男", "1152232@qq.com"));
        System.out.println(effectLine);
    }

    /**
     * 5,使用OParam注解命名参数
     * 此MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
     * a>以@Param注解的值为键，以参数为值
     * b>以param1,param2.·为键，以参数为值
     */
    @Test
    public void testCheckLoginByParam() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("王冉", "123456");
        System.out.println(user);
    }
}

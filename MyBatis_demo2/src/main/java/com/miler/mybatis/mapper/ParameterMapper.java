package com.miler.mybatis.mapper;

import com.miler.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author milerliu
 * @Date 2022/3/29 21:07
 * @Version 1.0
 */
public interface ParameterMapper {
    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getAllUser();

    /**
     * 根据username获取用户
     * @param name
     * @return
     */
    User getUserByUsername(String name);

    /**
     * 通过多个变量传递多个参数
     * @param username
     * @param password
     * @return 返回登录用户
     */
    User checkLogin(String username, String password);

    /**
     * 通过Map传递多个参数
     * @param params
     * @return
     */
    User checkLoginByMap(Map<String, Object> params);

    /**
     *  通过对象传递参数
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 使用@Param 传递参数
     * @param
     * @return
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);

}

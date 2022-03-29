package com.miler.mybatis.mapper;

import com.miler.mybatis.pojo.User;

import java.util.List;

/**
 * @Author milerliu
 * @Date 2022/3/29 17:01
 * @Version 1.0
 */
public interface UserMapper {
    /**
     * 添加用户
     * @return 返回受影响的行数
     */
    int insertUser();

    /**
     * 修改用户信息
     */
    void updateUser();

    /**
     * 删除用户信息
     */
    void deleteUser();

    /**
     * 根据id查询一条数据
     * @return
     */
    User getUserById();

    /**
     * 获取所有的用户信息
     * @return
     */
    List<User> getAllUser();
}

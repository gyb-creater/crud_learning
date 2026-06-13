package com.huse.demo.dao;

import com.huse.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 标记这是一个 MyBatis 的 Mapper 接口，用来声明对 user 表的数据库操作
@Mapper
public interface UserMapper {

    // 按条件分页查询用户列表
    List<User> selectPage(@Param("name") String name,
                          @Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize);

    // 统计符合条件的用户总数，配合前端分页使用
    Long count(@Param("name") String name);

    // 根据主键 id 查询单个用户
    User selectById(@Param("id") Long id);

    // 根据用户名查询用户，常用于注册时校验重名
    User selectByName(@Param("name") String name);

    // 根据用户名、密码和角色查询用户，常用于登录校验
    User selectByLogin(@Param("name") String name,
                       @Param("password") String password,
                       @Param("level") String level);

    // 新增用户
    int insert(User user);

    // 修改用户
    int update(User user);

    // 删除用户
    int deleteById(@Param("id") Long id);
}

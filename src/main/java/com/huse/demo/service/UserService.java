package com.huse.demo.service;

import com.huse.demo.common.PageResult;
import com.huse.demo.dao.UserMapper;
import com.huse.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 标记这是一个业务层组件，负责组织用户的增删改查、注册和登录逻辑
@Service
public class UserService {

    // 注入 MyBatis 的 UserMapper，用来执行数据库操作
    @Resource
    private UserMapper userMapper;

    /**
     * 新增用户
     *
     * @param user 用户对象
     */
    public void add(User user) {
        fillDefaultCreateTime(user);
        fillDefaultAuthInfo(user);
        userMapper.insert(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户对象
     */
    public void update(User user) {
        userMapper.update(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户主键 id
     */
    public void del(Long id) {
        userMapper.deleteById(id);
    }

    /**
     * 根据 id 查询单个用户
     *
     * @param id 用户主键 id
     * @return 用户对象
     */
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 分页查询用户
     *
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @param name 用户名关键字
     * @return 分页结果
     */
    public PageResult<User> findPage(Integer pageNum, Integer pageSize, String name) {
        int safePageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        int safePageSize = pageSize == null || pageSize < 1 ? 8 : pageSize;
        int offset = (safePageNum - 1) * safePageSize;

        List<User> list = userMapper.selectPage(name, offset, safePageSize);
        Long total = userMapper.count(name);

        PageResult<User> pageResult = new PageResult<>();
        pageResult.setContent(list);
        pageResult.setTotalElements(total);
        pageResult.setPageNum(safePageNum);
        pageResult.setPageSize(safePageSize);
        pageResult.setTotalPages((int) Math.ceil(total * 1.0 / safePageSize));
        return pageResult;
    }

    /**
     * 注册用户
     *
     * @param user 注册时提交的用户信息
     * @return 注册成功后的用户对象
     */
    public User register(User user) {
        fillDefaultCreateTime(user);
        fillDefaultAuthInfo(user);
        userMapper.insert(user);
        return user;
    }

    /**
     * 登录校验
     *
     * @param user 登录时提交的用户名、密码和角色
     * @return 匹配到的用户对象
     */
    public User login(User user) {
        return userMapper.selectByLogin(user.getName(), user.getPassword(), user.getLevel());
    }

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户对象
     */
    public User findByName(String name) {
        return userMapper.selectByName(name);
    }

    // 注册或新增时如果没传创建时间，就自动补一个当前日期
    private void fillDefaultCreateTime(User user) {
        if (user.getCreateTime() == null || "".equals(user.getCreateTime().trim())) {
            user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
    }

    // 如果页面没有传密码或角色，就给一个默认值，避免新增数据时缺少认证字段
    private void fillDefaultAuthInfo(User user) {
        if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
            user.setPassword("123456");
        }
        if (user.getLevel() == null || "".equals(user.getLevel().trim())) {
            user.setLevel("3");
        }
    }
}

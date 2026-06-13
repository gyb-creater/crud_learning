package com.huse.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// 标记这是一个实体类，对应数据库中的 user 表
@Entity
@Table(name = "user")
public class User {

    // 主键字段，对应数据库中的自增 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名
    private String name;

    // 年龄
    private Integer age;

    // 性别
    private String sex;

    // 地址
    private String address;

    // 电话
    private String phone;

    // 把 createTime 映射到数据库中的 create_time 字段
    @Column(name = "create_time")
    private String createTime;

    // 登录密码，用于注册和登录校验
    private String password;

    // 用户角色，1 表示管理员，2 表示教师，3 表示学生
    private String level;

    // 获取主键 id
    public Long getId() {
        return id;
    }

    // 设置主键 id
    public void setId(Long id) {
        this.id = id;
    }

    // 获取用户名
    public String getName() {
        return name;
    }

    // 设置用户名
    public void setName(String name) {
        this.name = name;
    }

    // 获取年龄
    public Integer getAge() {
        return age;
    }

    // 设置年龄
    public void setAge(Integer age) {
        this.age = age;
    }

    // 获取性别
    public String getSex() {
        return sex;
    }

    // 设置性别
    public void setSex(String sex) {
        this.sex = sex;
    }

    // 获取地址
    public String getAddress() {
        return address;
    }

    // 设置地址
    public void setAddress(String address) {
        this.address = address;
    }

    // 获取电话
    public String getPhone() {
        return phone;
    }

    // 设置电话
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 获取创建时间
    public String getCreateTime() {
        return createTime;
    }

    // 设置创建时间
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    // 获取密码
    public String getPassword() {
        return password;
    }

    // 设置密码
    public void setPassword(String password) {
        this.password = password;
    }

    // 获取角色等级
    public String getLevel() {
        return level;
    }

    // 设置角色等级
    public void setLevel(String level) {
        this.level = level;
    }
}

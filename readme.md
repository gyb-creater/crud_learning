# Student 用户管理项目

## 1. 项目简介

这是一个基于 `Spring Boot + MyBatis + MySQL + Vue2 + Element UI + jQuery` 的基础用户管理系统。

项目目前实现了以下核心功能：

- 用户登录
- 用户注册
- 用户分页查询
- 用户模糊搜索
- 用户新增
- 用户编辑
- 用户删除
- 前后端真实联动

这个项目的定位很适合：

- Java Web 课程设计
- Spring Boot 入门练习
- MyBatis 基础练习
- 前后端分离风格的基础 CRUD 项目
- 面试项目讲解素材

---

## 2. 技术栈

### 后端

- `Spring Boot 2.3.12.RELEASE`
- `MyBatis`
- `MySQL`
- `Maven`

### 前端

- `Vue 2`
- `Element UI`
- `jQuery`
- 原生 `HTML + CSS + JavaScript`

---

## 3. 项目功能说明

### 3.1 登录功能

登录页地址：

- `http://localhost:8080/page/login.html`

功能说明：

- 输入用户名
- 输入密码
- 选择角色
- 调用后端 `/user/login`
- 登录成功后把用户信息保存到 `localStorage`
- 自动跳转到 `index.html`

### 3.2 注册功能

注册页地址：

- `http://localhost:8080/page/register.html`

功能说明：

- 输入用户名
- 输入密码
- 确认密码
- 输入年龄、性别、地址、电话
- 选择角色
- 调用后端 `/user/register`
- 注册成功后跳转回登录页

### 3.3 用户列表功能

首页地址：

- `http://localhost:8080/index.html`

功能说明：

- 分页展示用户信息
- 支持用户名关键字搜索
- 支持新增用户
- 支持编辑用户
- 支持删除用户
- 未登录时自动跳转到登录页

---

## 4. 项目结构

```text
student
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com.huse.demo
│  │  │     ├─ common
│  │  │     │  ├─ PageResult.java
│  │  │     │  ├─ Result.java
│  │  │     │  └─ WebMvcConfig.java
│  │  │     ├─ controller
│  │  │     │  └─ UserController.java
│  │  │     ├─ dao
│  │  │     │  └─ UserMapper.java
│  │  │     ├─ entity
│  │  │     │  └─ User.java
│  │  │     ├─ service
│  │  │     │  └─ UserService.java
│  │  │     └─ StudentApplication.java
│  │  └─ resources
│  │     ├─ mapper
│  │     │  └─ UserMapper.xml
│  │     ├─ static
│  │     │  ├─ index.html
│  │     │  ├─ element.css
│  │     │  ├─ element.js
│  │     │  ├─ jquery.min.js
│  │     │  ├─ vue.js
│  │     │  └─ page
│  │     │     ├─ login.html
│  │     │     └─ register.html
│  │     ├─ application.yml
│  │     └─ user-auth-update.sql
├─ pom.xml
└─ README.md
```

---

## 5. 核心设计说明

### 5.1 分层结构

本项目采用典型的分层架构：

#### 1. Controller 层

作用：

- 接收前端 HTTP 请求
- 调用 Service 层处理业务
- 返回统一格式的 JSON 数据

本项目对应类：

- `UserController`

#### 2. Service 层

作用：

- 编写业务逻辑
- 处理分页参数
- 处理默认值
- 调用 Mapper 操作数据库

本项目对应类：

- `UserService`

#### 3. DAO / Mapper 层

作用：

- 直接和数据库交互
- 编写 SQL
- 定义接口方法

本项目对应文件：

- `UserMapper.java`
- `UserMapper.xml`

#### 4. Entity 层

作用：

- 对应数据库表结构
- 作为前后端数据传输对象

本项目对应类：

- `User`

#### 5. Common 层

作用：

- 放公共结果封装
- 放分页结果对象
- 放跨域配置

本项目对应类：

- `Result`
- `PageResult`
- `WebMvcConfig`

---

## 6. 统一返回结果设计

项目中定义了统一响应对象 `Result<T>`。

作用：

- 统一后端接口返回格式
- 让前端更容易处理成功/失败逻辑
- 方便后续扩展错误码

返回格式示例：

```json
{
  "code": "0",
  "msg": "成功",
  "data": {
    "name": "张三"
  }
}
```

常见字段说明：

- `code`：状态码，`0` 表示成功，其他表示失败
- `msg`：提示信息
- `data`：实际业务数据

---

## 7. 分页设计说明

项目中自定义了 `PageResult<T>`。

作用：

- 给前端返回分页数据
- 兼容 Element UI 分页组件

主要字段：

- `content`：当前页数据列表
- `totalElements`：总记录数
- `pageNum`：当前页码
- `pageSize`：每页条数
- `totalPages`：总页数

前端 `el-table` 读取：

- `page.content`

前端 `el-pagination` 读取：

- `page.totalElements`

---

## 8. 后端接口说明

### 8.1 用户列表分页接口

请求方式：

- `GET /user/page`

请求参数：

- `pageNum`：当前页码
- `pageSize`：每页条数
- `name`：用户名关键字

作用：

- 分页查询用户
- 支持按用户名模糊搜索

### 8.2 查询单个用户

请求方式：

- `GET /user/{id}`

作用：

- 根据 id 查询用户信息

### 8.3 新增用户

请求方式：

- `POST /user`

作用：

- 新增用户记录

### 8.4 修改用户

请求方式：

- `PUT /user`

作用：

- 修改已有用户

### 8.5 删除用户

请求方式：

- `DELETE /user/{id}`

作用：

- 删除用户

### 8.6 注册接口

请求方式：

- `POST /user/register`

作用：

- 注册新用户
- 校验用户名是否重复

### 8.7 登录接口

请求方式：

- `POST /user/login`

作用：

- 根据用户名、密码、角色进行登录校验

---

## 9. 前端交互流程

### 9.1 登录流程

1. 用户在 `login.html` 输入用户名、密码、角色
2. 前端通过 `$.ajax` 调用 `/user/login`
3. 后端校验成功后返回用户信息
4. 前端保存到 `localStorage`
5. 跳转到 `index.html`

### 9.2 注册流程

1. 用户在 `register.html` 填写注册信息
2. 前端校验两次密码是否一致
3. 调用 `/user/register`
4. 后端检查用户名是否存在
5. 注册成功后跳转回登录页

### 9.3 用户列表加载流程

1. 页面 `created()` 生命周期中调用 `loadTable(pageNum)`
2. 通过 `$.get('/user/page')` 获取分页数据
3. 将结果赋值给 `page`
4. `el-table` 自动渲染 `page.content`
5. `el-pagination` 自动根据总数渲染页码

### 9.4 新增/编辑流程

1. 点击新增按钮，打开弹窗
2. 点击编辑按钮，回填当前行数据
3. 点击确定时：
   - 没有 `id`，走新增
   - 有 `id`，走修改
4. 成功后重新加载列表

### 9.5 删除流程

1. 点击删除按钮
2. 前端弹出确认框
3. 确认后调用删除接口
4. 删除成功后刷新当前页或第一页

---

## 10. 如何运行项目

### 10.1 数据库准备

1. 创建数据库 `mytest`
2. 执行本项目提供的 SQL 文件

推荐使用我下面给你的完整数据库脚本。

### 10.2 修改数据库连接

配置文件位置：

- `src/main/resources/application.yml`

当前示例配置：

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2b8
```

你需要根据自己本机数据库账号密码进行调整。

### 10.3 启动项目

方式一：

```bash
mvn spring-boot:run
```

方式二：

- 直接运行 `StudentApplication.java`

### 10.4 访问页面

登录页：

- `http://localhost:8080/page/login.html`

注册页：

- `http://localhost:8080/page/register.html`

用户列表页：

- `http://localhost:8080/index.html`

---

## 11. 常见面试知识点总结

下面这些内容你可以直接拿去做面试复盘。

### 11.1 Spring Boot 是什么

Spring Boot 是 Spring 官方推出的快速开发框架。

它的核心价值：

- 简化 Spring 项目配置
- 提供自动装配
- 内置 Tomcat，能独立运行
- 方便整合 MyBatis、Redis、MQ 等中间件

面试可答：

> Spring Boot 的核心优势是约定大于配置，通过自动装配减少 XML 和手动配置，让项目能更快搭建和启动。

### 11.2 自动装配原理

关键词：

- `@SpringBootApplication`
- `@EnableAutoConfiguration`
- `spring.factories`

原理简述：

1. 启动类加 `@SpringBootApplication`
2. 内部开启自动配置
3. Spring Boot 根据类路径中的依赖自动加载配置类
4. 比如引入 MyBatis、Web、DataSource 后，会自动创建相关 Bean

面试可答：

> 自动装配本质是根据依赖和条件注解决定是否创建 Bean，减少手写配置。

### 11.3 MyBatis 和 JPA 的区别

#### MyBatis

特点：

- SQL 自己写
- 灵活度高
- 适合复杂查询

#### JPA

特点：

- ORM 思想更强
- 更偏向对象操作
- 简单 CRUD 很方便

本项目最终采用的是：

- `MyBatis`

原因：

- 更适合教学和理解 SQL
- `UserMapper.xml` 能清晰看到 SQL 逻辑

### 11.4 MyBatis 的执行流程

简述：

1. Controller 调用 Service
2. Service 调用 Mapper 接口
3. Mapper 接口绑定 XML 中的 SQL
4. MyBatis 执行 SQL
5. 把结果映射成实体对象返回

核心文件：

- `UserMapper.java`
- `UserMapper.xml`

### 11.5 为什么要写 Mapper.xml

原因：

- SQL 和 Java 代码解耦
- 复杂 SQL 更清晰
- 更方便后期维护和优化

### 11.6 resultMap 的作用

本项目中 `create_time` 对应实体属性 `createTime`，字段名和属性名不一致。

这时需要通过 `resultMap` 做映射：

```xml
<result column="create_time" property="createTime"/>
```

面试重点：

- 当数据库字段和 Java 属性名不一致时，需要使用 `resultMap`

### 11.7 分页是怎么做的

本项目使用的是手动分页。

核心思路：

1. 前端传 `pageNum` 和 `pageSize`
2. 后端计算 `offset = (pageNum - 1) * pageSize`
3. SQL 使用 `limit offset, pageSize`
4. 额外查询 `count(*)`
5. 返回总数和当前页数据

优点：

- 简单直接
- 便于理解分页原理

### 11.8 为什么要统一返回 Result

原因：

- 前端只需要判断 `code`
- 不同接口风格一致
- 更方便后续扩展错误处理

面试可以说：

> 统一响应结构可以降低前后端沟通成本，也方便全局异常处理和接口规范管理。

### 11.9 前后端是怎么联动的

本项目的联动方式：

- 前端使用 `jQuery ajax`
- 后端使用 Spring Boot 提供 REST 接口
- 前端发 JSON
- 后端通过 `@RequestBody` 接收

例如新增用户：

1. 前端弹窗收集表单数据
2. `$.ajax` 发送 `POST /user`
3. 后端 `@PostMapping` 接收
4. Service 调用 Mapper 执行插入
5. 返回 `Result.success()`
6. 前端提示成功并刷新表格

### 11.10 GET、POST、PUT、DELETE 的区别

#### GET

- 查询数据

#### POST

- 新增数据

#### PUT

- 修改数据

#### DELETE

- 删除数据

本项目对应：

- `GET /user/page`：分页查询
- `POST /user`：新增
- `PUT /user`：修改
- `DELETE /user/{id}`：删除

### 11.11 为什么登录后把用户信息存 localStorage

作用：

- 刷新页面后仍能读取登录状态
- 不需要每次都重新登录

缺点：

- 安全性一般
- 不适合生产级认证

面试时要补一句：

> 这个项目是教学项目，登录态暂时用 localStorage 保存；如果是正式项目，通常会使用 Session、JWT 或者 Token 方案。

### 11.12 本项目的不足

这个问题在面试里经常会被追问。

你可以这样答：

1. 密码目前是明文存储，不安全
2. 登录态使用 localStorage，缺少真正的鉴权拦截
3. 没有做统一异常处理
4. 没有参数校验框架，比如 Hibernate Validator
5. 没有做密码加密，比如 MD5、BCrypt
6. 没有使用分页插件，如 PageHelper
7. 前端没有封装请求模块
8. 没有做权限控制，不同角色登录后页面还未区分

### 11.13 如果继续优化，可以怎么做

优化方向：

1. 使用 `BCrypt` 加密密码
2. 引入 `JWT` 做登录鉴权
3. 引入 `PageHelper` 做 MyBatis 分页
4. 加入全局异常处理 `@ControllerAdvice`
5. 使用 `Lombok` 简化实体类
6. 引入 `Hibernate Validator` 做参数校验
7. 前端改成 `axios + Vue Router`
8. 加入管理员、教师、学生的角色权限区分
9. 把前端拆成真正组件化工程，比如 Vue CLI / Vite

### 11.14 面试时如何介绍这个项目

你可以参考下面这段：

> 这个项目是一个基于 Spring Boot、MyBatis、MySQL 和 Vue2 + Element UI 实现的用户管理系统。  
> 后端采用分层架构，包括 Controller、Service、Mapper、Entity 和 Common 层。  
> 核心实现了用户注册、登录、分页查询、模糊搜索、增删改查等功能。  
> 数据层使用 MyBatis + XML 编写 SQL，前端通过 jQuery ajax 调用 REST 接口实现真实联动。  
> 在项目里我重点练习了统一返回结构、分页封装、前后端交互以及 MyBatis 映射配置。  
> 如果继续优化，我会进一步加入密码加密、JWT 鉴权、全局异常处理和权限控制。

---

## 12. 常见问题排查

### 12.1 启动时报数据库连接失败

检查：

- MySQL 是否启动
- 数据库名是否是 `mytest`
- 用户名和密码是否正确
- `application.yml` 是否和本机一致

### 12.2 登录失败

检查：

- 数据库里是否有该用户
- `password` 和 `level` 字段是否存在
- 用户名、密码、角色是否匹配

### 12.3 注册失败

检查：

- 用户名是否重复
- 数据表是否已经补了 `password`、`level` 字段

### 12.4 页面能打开但没有数据

检查：

- `/user/page` 接口是否正常返回
- 数据库里是否有用户数据
- 浏览器控制台是否报错

---

## 13. 数据库脚本说明

项目必须使用包含认证字段的 `user` 表。

你可以直接使用我下面提供的完整 SQL 脚本，或者使用项目里的：

- `src/main/resources/user-auth-update.sql`

但更推荐直接用完整初始化脚本。

---

## 14. 总结

这个项目虽然不大，但已经包含了一个典型 Java Web 项目的核心训练点：

- Spring Boot 基础搭建
- MyBatis 的接口与 XML 映射
- MySQL 表设计
- 统一返回结果封装
- 分页查询封装
- 前后端真实交互
- 登录注册流程
- Element UI 基础页面开发

如果你是拿它去做课程设计、答辩或者面试项目讲解，这一套内容已经足够支撑你讲清楚：

- 项目做了什么
- 技术怎么选
- 后端怎么分层
- SQL 怎么写
- 前端怎么调用接口
- 还有哪些地方可以优化


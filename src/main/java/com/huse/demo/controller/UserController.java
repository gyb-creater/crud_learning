package com.huse.demo.controller;

import com.huse.demo.common.PageResult;
import com.huse.demo.common.Result;
import com.huse.demo.entity.User;
import com.huse.demo.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

// 标记这是一个控制层类，用来接收前端请求并返回 JSON 数据
@RestController
// 当前控制器下的接口统一使用 /user 作为访问前缀
@RequestMapping("/user")
public class UserController {

    // 注入业务层对象，用来处理用户相关的业务逻辑
    @Resource
    private UserService userService;

    /**
     * 新增用户
     *
     * @param user 前端提交的用户对象
     * @return 统一响应结果
     */
    @PostMapping
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 修改用户
     *
     * @param user 前端提交的用户对象
     * @return 统一响应结果
     */
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * 删除用户
     *
     * @param id 用户主键 id
     * @return 统一响应结果
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        userService.del(id);
        return Result.success();
    }

    /**
     * 根据 id 查询用户
     *
     * @param id 用户主键 id
     * @return 用户对象
     */
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        clearPassword(user);
        return Result.success(user);
    }

    /**
     * 分页模糊查询用户
     *
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @param name 用户名关键字
     * @return 分页结果
     */
    @GetMapping
    public Result<PageResult<User>> findPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "8") Integer pageSize,
                                             @RequestParam(required = false) String name) {
        PageResult<User> pageResult = userService.findPage(pageNum, pageSize, name);
        clearPasswordInPage(pageResult);
        return Result.success(pageResult);
    }

    /**
     * 保留 /page 路径，方便前端直接按照页面中的写法调用
     *
     * @param pageNum 当前页码
     * @param pageSize 每页条数
     * @param name 用户名关键字
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<PageResult<User>> findPageByPath(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "8") Integer pageSize,
                                                   @RequestParam(required = false) String name) {
        PageResult<User> pageResult = userService.findPage(pageNum, pageSize, name);
        clearPasswordInPage(pageResult);
        return Result.success(pageResult);
    }

    /**
     * 注册用户
     *
     * @param user 注册时提交的用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        if (user.getName() == null || "".equals(user.getName().trim())) {
            return Result.error("-1", "用户名不能为空");
        }
        if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
            return Result.error("-1", "密码不能为空");
        }
        if (user.getLevel() == null || "".equals(user.getLevel().trim())) {
            return Result.error("-1", "请选择角色");
        }
        if (userService.findByName(user.getName()) != null) {
            return Result.error("-1", "用户名已存在");
        }
        User registerUser = userService.register(user);
        clearPassword(registerUser);
        return Result.success(registerUser);
    }

    /**
     * 登录接口
     *
     * @param user 登录时提交的用户名、密码和角色
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        if (user.getName() == null || "".equals(user.getName().trim())) {
            return Result.error("-1", "请输入用户名");
        }
        if (user.getPassword() == null || "".equals(user.getPassword().trim())) {
            return Result.error("-1", "请输入密码");
        }
        if (user.getLevel() == null || "".equals(user.getLevel().trim())) {
            return Result.error("-1", "请选择角色");
        }
        User loginUser = userService.login(user);
        if (loginUser == null) {
            return Result.error("-1", "用户名、密码或角色错误");
        }
        clearPassword(loginUser);
        return Result.success(loginUser);
    }

    // 返回给前端前主动清空密码字段，避免密码泄露
    private void clearPassword(User user) {
        if (user != null) {
            user.setPassword(null);
        }
    }

    // 把分页结果中的每个用户密码都清空
    private void clearPasswordInPage(PageResult<User> pageResult) {
        if (pageResult != null && pageResult.getContent() != null) {
            for (User user : pageResult.getContent()) {
                clearPassword(user);
            }
        }
    }
}

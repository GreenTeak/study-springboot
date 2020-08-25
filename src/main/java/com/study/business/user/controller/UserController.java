package com.study.business.user.controller;

import com.alibaba.fastjson.JSON;
import com.study.business.user.entity.User;
import com.study.business.user.service.UserService;
import com.study.exception.GenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户增删改查
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    //------------------------------传参测试接口 start-----------------------------------
    //显示用户
    @GetMapping("listUser")
    public List<User> listUser() {
        return userService.getUser();
    }

    // 增加用户
    // 传参方式:URL拼接参数 127.0.0.1:8815/add?id=3&age=29&userName=张三封
    @GetMapping("/addUser")
    public String addUser(@RequestParam(value = "id", required = true) Long id,
                          @RequestParam(value = "age", required = true) Integer age,
                          @RequestParam(value = "userName", required = true) String userName) {
        user.setId(id);
        user.setAge(age);
        user.setUserName(userName);
        logger.info("增加用户：{}", user);
        userService.addUser(user);
        return "增加用户";
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity addUser(@RequestBody String userStr) {
        logger.info("接收到的参数：{}", userStr);
        User u = new User();
        User user = JSON.parseObject(userStr, u.getClass());
        logger.info("增加用户：{}", user);
        userService.addUser(user);
        return null;
    }

    //修改用户
    // 127.0.0.1:8815/user/update/2?age=33&userName=马云
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable(value = "id", required = true) Long id,
                             @RequestParam(value = "age", required = true) Integer age,
                             @RequestParam(value = "userName", required = true) String userName) {
        user.setId(id);
        user.setAge(age);
        user.setUserName(userName);
        logger.info("修改id为{}的用户为：{}", id, user);
        userService.updateUser(user);
        return "修改用户";
    }
    //------------------------------传参测试接口 end-----------------------------------


    //------------------------------web页面展示接口 start-----------------------------------
    // 删除用户
    // 传参方式:URL拼接参数
    @GetMapping("deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable(value = "id", required = true) int id) throws Exception {
        userService.deleteUser(id);
        logger.info("你已经删掉了id为:{}的用户", id);
        return new ModelAndView("redirect:/user/userList");
    }

    @GetMapping("/userList")
    public ModelAndView userList(Model model) {
        logger.info("调用userList");
        model.addAttribute("userList", userService.getUser());
        model.addAttribute("title", "用户管理");
//        return new ModelAndView("listUser", "userModel", model);
        return new ModelAndView("user/listUser", "userModel", model);
    }

    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "添加用户");
//        return new ModelAndView("addUser", "userModel", model);
        return new ModelAndView("user/addUser", "userModel", model);
    }

    @PostMapping("/add")
    public ModelAndView addUser(User user) {
        try {
            if (user.getId() == null) {
                throw new GenException("用户id不能为空");
            }
            if (user.getUserName() == null) {
                throw new GenException("用户名称不能为空");
            }
            if (user.getPassword() == null) {
                throw new GenException("用户密码不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        userService.addUser(user);
        return new ModelAndView("redirect:/user/userList");
    }


    // 登陆页面 2020-01-02
    @GetMapping("/loginWeb")
    public ModelAndView loginWeb(Model model) {
        logger.info("登陆页面");
        model.addAttribute("user", new User());
        model.addAttribute("title", "用户登陆");
        return new ModelAndView("userLogin", "userModel", model);
    }

    // 登陆 2020-01-02
    @PostMapping("/login")
    public ModelAndView login(User user) {
        logger.info("登陆 user {}", user);
        Boolean isLogin = userService.login(user);
        if (isLogin) {
            return new ModelAndView("redirect:/user/userList");
        } else {
            return new ModelAndView("redirect:/user/loginWeb");
        }
    }
//------------------------------web页面展示接口 end-----------------------------------

}

package com.study.business.people.controller;

import com.study.business.people.entity.PeUser;
import com.study.business.people.service.PeUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Controller
public class PeUserController {
    private static final Logger logger = LoggerFactory.getLogger(PeUserController.class);

    @Resource
    PeUserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        logger.info("============================");
        Page<PeUser> peUsers = userService.getUserList(pageNum, pageSize);
        logger.info("总页数" + peUsers.getTotalPages());
        logger.info("当前页是：" + pageNum);

        logger.info("分页数据：");
        Iterator<PeUser> u = peUsers.iterator();
        while (u.hasNext()) {
            logger.info(u.next().getUserName());
        }

        model.addAttribute("peUsers", peUsers);
        return "peuser/listPeUser";
    }


    // 构造用户信息，并插入数据库
    @RequestMapping("/build")
    public void buildPeUser() {
        // 插入十条数据，每天一条
        List<PeUser> peUsers = userService.buildPeUser(10);
        logger.info("创建了{}条数据", peUsers.size());

    }


}

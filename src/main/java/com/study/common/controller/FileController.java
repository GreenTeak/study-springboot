package com.study.common.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.study.common.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 文件操作接口
 */
@EnableScheduling
@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${file.source}")
    private String fileSource;

    @Value("${file.target}")
    private String fileTarget;

    @Autowired
    private FileService fileService;

    // 复制
    @RequestMapping("/copy")
    public @ResponseBody
    String copy() {
        String result = "";
        try {
            fileService.copy(fileSource, fileTarget);
            result = "复制成功！";
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = e.getMessage();
        }
        return result;
    }

    @PostMapping("/delete")
    public void delete(@PathVariable(value = "path", required = true) JSONPObject json) {
        logger.info("要删除的路径：{}", json);
        String path = (String) json.getValue();

        fileService.delete(path);
    }


//    @Scheduled(cron = "*/5 * * * * ?")
//    public void synLog() {
//        logger.info("定时任务开始 cron = */5 * * * * ?");
//        logger.info("定时任务结束");
//    }

}

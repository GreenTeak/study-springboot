package com.study.common.service.impl;

import com.study.common.service.FileService;
import com.study.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 复制日志到新路径下
     *
     * @param sourcePath 源路径
     * @param targetPath 目标路径
     * @return
     */
    @Override
    public void copy(String sourcePath, String targetPath) throws Exception {
        FileUtil fileUtil = new FileUtil();
        List<String> pathAndNames = fileUtil.getAllFilePath(sourcePath);
        FileUtil.makeDirs(targetPath, "");
        // 将原路经的文件全部复制到新的路径下，并将原路经下的文件全部删除（剪贴）
        for (String pathAndName : pathAndNames) {
            logger.info("复制{}到路径：{}", pathAndName, targetPath);
            FileUtil.copy(pathAndName, targetPath);
            FileUtil.deleteDir(new File(pathAndName));
        }
    }

    /**
     * 删除文件的
     *
     * @param fileName
     */
    public void delete(String fileName) {
        logger.info("删除的文件：{}", fileName);
        File file = new File(fileName);
        FileUtil.deleteDir(file);
    }
}

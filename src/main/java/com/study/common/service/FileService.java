package com.study.common.service;

public interface FileService {

    /**
     * 剪贴日志到新路径下
     *
     * @param sourcePath 源路径
     * @param targetPath 目标路径
     * @return
     */
    void copy(String sourcePath, String targetPath) throws Exception;

    /**
     * 删除该路径下的所有文件，及子路经下的文件
     *
     * @param fileName
     */
    void delete(String fileName);


}

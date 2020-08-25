package com.study.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件帮助类
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private List<String> filePathList = new ArrayList<>();

    /**
     * 获取一个路径下的所有文件(包含子文件夹)
     *
     * @param rootPath
     * @return
     */
    public List<String> getAllFilePath(String rootPath) {
        File file = new File(rootPath);
        File[] files = file.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getAllFilePath(files[i].getPath());
            } else {
                filePathList.add(files[i].getPath());
            }
        }
        return filePathList;
    }

    /**
     * 获取一个路径下的所有文件(包含子文件夹) 可以指定过滤规则
     *
     * @param rootPath
     * @param fileFilter
     * @return
     */
    public List<String> getAllFilePath(String rootPath, FileFilter fileFilter) {
        File file = new File(rootPath);
        File[] files = file.listFiles(fileFilter);

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getAllFilePath(files[i].getPath(), fileFilter);
            } else {
                filePathList.add(files[i].getPath());
            }
        }
        return filePathList;
    }

    /**
     * 删除空目录
     *
     * @param dir 将要删除的目录路径
     */
    public static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 使用文件通道的方式复制文件
     *
     * @param srcPath  源文件
     * @param descPath 复制到的新文件
     */
    public static void copy(String srcPath, String descPath) {
        File s = new File(srcPath);
        File t = new File(descPath + "/" + s.getName());

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对当前文件夹下的文件改名
     *
     * @param oldFilePath
     * @param newName
     */
    public static void reName(String oldFilePath, String newName) {
        String basePath = oldFilePath.substring(0, oldFilePath.lastIndexOf("/"));
        String descPaht = basePath + "/" + newName;
        reNameTo(oldFilePath, descPaht);
    }

    /**
     * 对当前文件夹下的文件改名
     *
     * @param oldFile
     * @param newName
     */
    public static void reName(File oldFile, String newName) {
        String oldFilePath = oldFile.getPath();
        String basePath = oldFilePath.substring(0, oldFilePath.lastIndexOf("\\"));
        String descPaht = basePath + "/" + newName;
        reNameTo(oldFile, descPaht);
    }

    /**
     * 在目标目录下创建 文件夹
     *
     * @param descPath
     * @param dirName
     */
    public static void makeDirs(String descPath, String dirName) {
        File file = new File(descPath);

        if (file != null) {
            new File(descPath + "/" + dirName).mkdirs();
        } else {
            System.out.println("目标目录不为空");
        }
    }

    private static void reNameTo(File oldFile, String newFilePath) {
        oldFile.renameTo(new File(newFilePath));
    }

    private static void reNameTo(String oldFilePath, String newFilePath) {
        File oldFile = new File(oldFilePath);
        oldFile.renameTo(new File(newFilePath));
    }


    /**
     * 生成文件
     *
     * @param file   文件路径+文件名称
     * @param conent 要生成的文件内容
     * @date 20191009
     */
    public static void appendWriteFile(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent);
        } catch (Exception e) {
            logger.error("写文件:[" + file + "]异常，异常信息为:[" + e.getMessage() + "]");
        } finally {
            logger.info("开始关闭输出流");
            try {
                out.close();
            } catch (IOException e) {
                logger.info("关闭输出流异常，异常信息为:[" + e.getMessage() + "]");
            }
        }

    }

//    public static void main(String[] args) {
//        //200 表示生成200行
//        for (int i = 0; i < 200; i++) {
//            appendWriteFile("E:\\20191009.log",
//                    "西安荼艺科技有限公司@|@610005222" + i + "@|@20141010@|@32.01@|@67.78@|@555@|@50@|@10000@|@20180102@|@01@|@02@|@201805\n");
//        }
//    }


}

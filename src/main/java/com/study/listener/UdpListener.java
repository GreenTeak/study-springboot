//package com.study.listener;
//
//import com.study.common.util.FileUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.SocketException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@WebListener
//public class UdpListener implements ServletContextListener {
//    private static final Logger logger = LoggerFactory.getLogger(UdpListener.class);
//    // 换行符
//    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
//    // 路径分隔符
//    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
//
//    public static final int MAX_UDP_DATA_SIZE = 4096;
//
//    @Value("${udp.port}")
//    private int udpPort;
//
//    @Value("${file.source}")
//    private String fileSource;
//
//    // 当Servlet容器启动Web应用时调用该方法。在调用完该方法之后，容器再对Filter初始化，
//    // 并且对那些在Web应用启动时就需要被初始化的Servlet进行初始化。
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        logger.info("******ServletContextListener.contextInitialized() Servlet容器初始化之前 UdpListener 监听器初始化******");
//        try {
//            // 启动一个线程，监听UDP数据报
//            new Thread(new UdpProcess(udpPort)).start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 当Servlet容器终止Web应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet和Filter过滤器。
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        logger.info("******ServletContextListener.contextDestroyed() Servlet容器终止之后 UdpListener 监听器销毁******");
//    }
//
//    class UdpProcess implements Runnable {
//        DatagramSocket socket = null;
//
//        public UdpProcess(final int port) throws SocketException {
//            socket = new DatagramSocket(port);
//        }
//
//        @Override
//        public void run() {
//            logger.info("--------- UdpProcess ---------");
//            while (true) {
//                byte[] buffer = new byte[MAX_UDP_DATA_SIZE];
//                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//                try {
//                    socket.receive(packet);
//                    new Thread(new Process(packet)).start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    class Process implements Runnable {
//        private final Logger logger = LoggerFactory.getLogger(Process.class);
//
//        public Process(DatagramPacket packet) throws UnsupportedEncodingException {
//            byte[] buffer = packet.getData();// 接收到的UDP信息，然后解码
//            String srt1 = new String(buffer, "GBK").trim();
//            String srt2 = new String(buffer, "UTF-8").trim();
//            String srt3 = new String(buffer, "ISO-8859-1").trim();
//            logger.info("=======Process srt1 GBK======" + srt1);
//            logger.info("=======Process srt2 UTF-8======" + srt2);
//            logger.info("=======Process srt3 ISO-8859-1======" + srt3);
//
//            // E:\铜仁警务云\test\source\syslog20191010.log
//            Date date = new Date();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//            String dateStr = simpleDateFormat.format(date);
//            String filePathAndName = fileSource + FILE_SEPARATOR + "syslog" + dateStr + ".log";
//            FileUtil.appendWriteFile(filePathAndName, srt2 + LINE_SEPARATOR);
//
//        }
//
//        @Override
//        public void run() {
//            logger.info("====Process run=====");
//        }
//    }
//
//}

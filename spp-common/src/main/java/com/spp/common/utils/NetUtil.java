package com.spp.common.utils;


import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 〈功能概述〉
 *
 * @author: zhougy
 * @date: 2024/5/14 17:16
 */
public class NetUtil {


    /**
     * 判断端口是否开启
     * @param ip
     * @param port
     * @param timeout
     * @return boolean
     */
    public static boolean isOpen(String ip, int port, int timeout) {
        InetSocketAddress address = new InetSocketAddress(ip, port);
        try (Socket sc = new Socket()) {
            sc.connect(address, timeout);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

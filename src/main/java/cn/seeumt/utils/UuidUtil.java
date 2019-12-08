package cn.seeumt.utils;

import java.util.UUID;

/**
 * @author Seeumt
 * @date 2019/12/8 11:29
 */
public class UuidUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}

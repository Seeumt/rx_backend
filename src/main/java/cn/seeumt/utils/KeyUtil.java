package cn.seeumt.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     *
     * @return
     */
    public static synchronized Long genUniqueKey() {
        Random random = new Random();
        Long number = random.nextInt(900000)+ 100000L;
        return System.currentTimeMillis() + number;
    }

    public static synchronized Long genUniqueUsername() {
        Random random = new Random();
        Long number = random.nextInt(9000000)+ 1000000L;
        return number;
    }

}

package cn.seeumt.service;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/21 20:13
 */
public interface RedisService {
    /**
     * 存储数据
     * @param key 键
     * @param value 值
     */
    void set(String key, String value);

    /**
     * 获取数据
     * @param key 键
     * @return String
     */
    String get(String key);

    /**
     * 设置超期时间
     * @param key 键
     * @param expire 过期时间
     * @return boolean
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
      * @param key 键
     */
    void remove(String key);

    /**
     * 自增操作
     * @param key 键
     * @param delta 自增步长
     * @return Long
     */
    Long increment(String key, long delta);

}

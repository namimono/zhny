package org.rcisoft.base.util;

import java.util.UUID;

/**
 * Create by 土豆儿
 * Time：2019/3/5 9:46
 **/

public class UuidUtil {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String create32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

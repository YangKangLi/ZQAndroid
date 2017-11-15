package cn.com.ziquan.lib.utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/11/15.
 */

public class MathDataUtil {

    /**
     * 去除double尾巴的0,如：50.00 --> 50
     *
     * @param d
     * @return
     */
    public static BigDecimal stripTrailingZeros(double d) {
        return new BigDecimal(d).stripTrailingZeros();
    }
}

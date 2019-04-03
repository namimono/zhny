package org.rcisoft.base.util;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;

/**
 * @author 土豆儿
 * @date 2019/4/3 11:00
 **/

public class FormulaUtil {

    /**
     * 通过Fel公式计算引擎计算出最终结果
     * @param formula （需将公式中的变量替换为对应的数值后传入）
     * @return 表达式计算后的结果
     */
    public static String getResult(String formula) {
        // 公式计算类
        FelEngine fel = new FelEngineImpl();
        // 结果
        String eval = "";
        // 判断表达式是否由数字和运算符号组成
        boolean flag = true;
        String[] split = formula.split("");
        for (String s : split) {
            if (!s.matches("\\d|\\(|\\)|\\+|\\-|\\*|/|%|\\.")) {
                flag = false;
                break;
            }
        }
        // 计算出公式的最终结果
        if (flag) {
            Object result = fel.eval(formula);
            if (result != null) {
                eval = result.toString();
            }
        }else {
            eval = null;
        }
        return eval;
    }
}

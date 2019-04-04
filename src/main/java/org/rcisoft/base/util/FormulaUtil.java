package org.rcisoft.base.util;

import com.alibaba.fastjson.JSONObject;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static String calculate(String formula, FelEngine fel) {
        // 结果
        String eval = null;
        // 判断表达式是否由数字和运算符号组成
        boolean flag = formula.matches("(\\d|\\(|\\)|\\+|\\-|\\*|/|%|\\.)+");
        // 计算出公式的最终结果
        if (flag) {
            Object result = fel.eval(formula);
            if (result != null) {
                eval = result.toString();
            }
        }
        return eval;
    }

    /**
     * 根据一级，二级code从json中获取数据
     * @param first
     * @param second
     * @param json
     * @return
     */
    public static String getValueFromJson(String first, String second, String json) {
        String result = null;
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject jsonFirst = jsonObject.getJSONObject(first);
        if (jsonFirst != null) {
            JSONObject regJson = jsonFirst.getJSONObject("REG_VAL");
            if (regJson != null) {
                result = regJson.getString(second);
            }
        }
        return result;
    }
}

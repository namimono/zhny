package org.rcisoft.base.util;

/**
 * @author 土豆儿
 * @date 2019/3/15 9:57
 **/

public class ExcelUtil {
    public static boolean isExcel2003(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

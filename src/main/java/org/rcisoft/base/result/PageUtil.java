package org.rcisoft.base.result;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by JiChao on 2018/7/26.
 */
public class PageUtil {

    public static PageInfo pageResult(List<?> list) {
        return new PageInfo<>(list);
    }

}

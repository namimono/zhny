package org.rcisoft.business.whole.home.service;

import org.rcisoft.business.whole.home.entity.HomeResult;
import org.rcisoft.business.whole.home.entity.ProjectHome;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 9:32 2019/4/10
 */
public interface HomeService {
    HomeResult queryProjectHome(HttpServletRequest request);
}

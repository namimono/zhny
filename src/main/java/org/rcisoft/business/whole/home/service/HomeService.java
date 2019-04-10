package org.rcisoft.business.whole.home.service;

import org.rcisoft.business.whole.home.entity.ProjectHome;

import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 9:32 2019/4/10
 */
public interface HomeService {
    List<ProjectHome> queryProjectHome(int year);
}

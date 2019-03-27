package org.rcisoft.business.management.evaluateproj.service;

import org.rcisoft.business.management.evaluateproj.entity.ProjectAssessment;

import java.util.List;

/**
 * Created by Minghui Xu on 2019-3-4
 */
public interface ProConfigService {
    /**
     * 获取关于项目的所有信息
     */
    List<ProjectAssessment> queryAllProjInfo();
}

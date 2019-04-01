package org.rcisoft.business.equipment.fault.service;

import com.github.pagehelper.PageInfo;
import org.rcisoft.business.equipment.fault.entity.FaultResult;
import org.rcisoft.entity.BusMalfunction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by JiChao on 2019/3/29.
 * 设备维护--故障统计
 */
public interface FaultService {

    /**
     * 故障数量统计
     * @param projectId
     * @param typeFirstId
     * @param year
     * @param month
     * @return
     */
    int[] queryFaultCount(String projectId, String typeFirstId, Integer year, Integer month);

    /**
     * 分页查询故障内容列表
     * @param projectId
     * @param typeFirstId
     * @param year
     * @param month
     * @return
     */
    PageInfo<FaultResult> queryFaults(String projectId, String typeFirstId, Integer year, Integer month);

    /**
     * 更新故障记录
     * @param busMalfunction
     * @return
     */
    int updateMalfunction(BusMalfunction busMalfunction);

    /**
     * 故障内容下载
     * @param projectId
     * @param typeFirstId
     * @param year
     * @param month
     */
    void downloadFaults(HttpServletRequest request, HttpServletResponse response, String projectId, String typeFirstId, Integer year, Integer month);

}

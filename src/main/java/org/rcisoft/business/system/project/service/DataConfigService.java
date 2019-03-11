package org.rcisoft.service.sysManagement.projMaintenance;

import com.github.pagehelper.PageInfo;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.vo.sysManagement.projMaintenance.ParamFirstAndSecond;

import java.util.List;
import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/6 14:48
 **/

public interface DataConfigService {

    /**
     * 查询系统类型信息
     */
    List<Map<String,Object>> querySysSystemInfo();

    /**
     * 查询一级参数信息
     */
    List<Map<String,Object>> queryParamFirstInfo(BusParamFirst busParamFirst);

    /**
     * 新增一级参数信息
     */
    String addParamFirstInfo(BusParamFirst busParamFirst);

    /**
     * 修改一级参数信息
     */
    int updateParamFirstInfo(BusParamFirst busParamFirst);

    /**
     * 查询二级参数信息
     */
    List<Map<String,Object>> queryParamSecondInfo(BusParamSecond busParamSecond);

    /**
     * 新增二级参数信息
     */
    int addParamSecondInfo(List<BusParamSecond> list);

    /**
     * 修改二级参数信息
     */
    int updateParamSecondInfo(List<BusParamSecond> list);

    /**
     * 数据配置联表查询一级、二级参数信息
     */
    PageInfo<ParamFirstAndSecond> queryDataParamForPage(String projectId);
}

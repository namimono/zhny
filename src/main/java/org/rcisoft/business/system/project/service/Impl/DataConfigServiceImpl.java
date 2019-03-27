package org.rcisoft.business.system.project.service.Impl;

import com.github.pagehelper.PageInfo;
import org.rcisoft.base.result.PageUtil;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.system.project.entity.ParamFirstAndSecond;
import org.rcisoft.business.system.project.service.DataConfigService;
import org.rcisoft.dao.BusParamFirstDao;
import org.rcisoft.dao.BusParamSecondDao;
//import org.rcisoft.dao.EnergyConfigDao;
import org.rcisoft.dao.SysSystemDao;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.entity.SysSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 土豆儿
 * Time：2019/3/6 14:49
 **/
@Service
public class DataConfigServiceImpl implements DataConfigService {

    @Autowired
    private SysSystemDao sysSystemDao;
    @Autowired
    private BusParamFirstDao busParamFirstDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;
//    @Autowired
//    private EnergyConfigDao energyConfigDao;

    /**
     * 查询系统类型信息
     */
    @Override
    public List<SysSystem> querySysSystemInfo(){
        return sysSystemDao.querySysSystemInfo();
    }

    /**
     * 根据项目ID查询一级参数信息
     */
    @Override
    public List<BusParamFirst> queryParamFirstInfo(BusParamFirst busParamFirst){
        return busParamFirstDao.queryParamFirstInfo(busParamFirst);
    }

    /**
     * 新增一级参数信息
     */
    @Override
    public ServiceResult addParamFirstInfo(BusParamFirst busParamFirst){
        String id = UuidUtil.create32();
        busParamFirst.setId(id);
        int i = busParamFirstDao.insert(busParamFirst);
        return new ServiceResult(i, id);
    }

    /**
     * 修改一级参数信息
     */
    @Override
    public int updateParamFirstInfo(BusParamFirst busParamFirst){
        return busParamFirstDao.updateByPrimaryKeySelective(busParamFirst);
    }

    /**
     * 查询二级参数信息
     */
    @Override
    public List<BusParamSecond> queryParamSecondInfo(BusParamSecond busParamSecond){
        return busParamSecondDao.queryParamSecondInfo(busParamSecond);
    }

    /**
     * 新增二级参数信息
     */
    @Override
    public int addParamSecondInfo(List<BusParamSecond> list){
        int sum = 0;
        for(BusParamSecond busParamSecond : list){
            busParamSecond.setId(UuidUtil.create32());
            busParamSecondDao.insert(busParamSecond);
            sum++;
        }
        return sum;
    }

    /**
     * 修改二级参数信息
     */
    @Override
    public int updateParamSecondInfo(List<BusParamSecond> list){
        int sum = 0;
        for(BusParamSecond busParamSecond : list){
            busParamSecond.setId(UuidUtil.create32());
            busParamSecondDao.updateByPrimaryKeySelective(busParamSecond);
            sum++;
        }
        return sum;
    }

    /**
     * 数据配置联表查询一级、二级参数信息
     */
    @Override
    public PageInfo<ParamFirstAndSecond> queryDataParamForPage(String projectId){
        return PageUtil.pageResult(busParamFirstDao.queryDataParam(projectId));
    }

//    /**
//     * 增加能源配置信息
//     */
//    @Override
//    public int addEnergyConfig(EnergyConfig energyConfig){
//        energyConfig.setId(UuidUtil.create32());
//        return energyConfigDao.insert(energyConfig);
//    }

//    /**
//     * 删除能源配置信息
//     */
//    @Override
//    public int deleteEnergyConfig(EnergyConfig energyConfig){
//        return energyConfigDao.deleteByPrimaryKey(energyConfigDao);
//    }

    /**
     * 删除一级参数信息
     */
    @Override
    public int deleteParamFirst(String id){
        return busParamFirstDao.deleteByPrimaryKey(id);
    }

    /**
     * 删除二级参数信息
     */
    @Override
    public int deleteParamSecond(String id){
        return busParamSecondDao.deleteByPrimaryKey(id);
    }
}

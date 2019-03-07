package org.rcisoft.service.sysManagement.projMaintenance.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.dao.BusParamFirstDao;
import org.rcisoft.dao.BusParamSecondDao;
import org.rcisoft.dao.SysSystemDao;
import org.rcisoft.entity.BusParamFirst;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.service.sysManagement.projMaintenance.DataConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询系统类型信息
     */
    @Override
    public List<Map<String,Object>> querySysSystemInfo(){
        return sysSystemDao.querySysSystemInfo();
    }

    /**
     * 查询一级参数信息
     */
    @Override
    public List<Map<String,Object>> queryParamFirstInfo(BusParamFirst busParamFirst){
        return busParamFirstDao.queryParamFirstInfo(busParamFirst);
    }

    /**
     * 新增一级参数信息
     */
    @Override
    public String addParamFirstInfo(BusParamFirst busParamFirst){
        busParamFirst.setId(UuidUtil.create32());
        busParamFirstDao.insert(busParamFirst);
        return busParamFirst.getId();
    }

    /**
     * 修改一级参数信息
     */
    public int updateParamFirstInfo(BusParamFirst busParamFirst){
        return busParamFirstDao.updateByPrimaryKeySelective(busParamFirst);
    }

    /**
     * 查询二级参数信息
     */
    public List<Map<String,Object>> queryParamSecondInfo(BusParamSecond busParamSecond){
        return busParamSecondDao.queryParamSecondInfo(busParamSecond);
    }

    /**
     * 新增二级参数信息
     */
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
    public int updateParamSecondInfo(List<BusParamSecond> list){
        int sum = 0;
        for(BusParamSecond busParamSecond : list){
            busParamSecond.setId(UuidUtil.create32());
            busParamSecondDao.updateByPrimaryKeySelective(busParamSecond);
            sum++;
        }
        return sum;
    }
}

package org.rcisoft.business.equipment.report.service.Impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.equipment.report.entity.VariableAndParam;
import org.rcisoft.business.equipment.report.service.FormulaOperationService;
import org.rcisoft.dao.BusFormulaDao;
import org.rcisoft.dao.BusParamSecondDao;
import org.rcisoft.dao.BusVariableDao;
import org.rcisoft.dao.SysSourceDao;
import org.rcisoft.entity.BusFormula;
import org.rcisoft.entity.BusParamSecond;
import org.rcisoft.entity.BusVariable;
import org.rcisoft.entity.SysSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/12 16:37
 **/
@Service
public class FormulaOperationServiceImpl implements FormulaOperationService {

    @Autowired
    private BusFormulaDao busFormulaDao;
    @Autowired
    private BusVariableDao busVariableDao;
    @Autowired
    private SysSourceDao sysSourceDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;

    /**
     * 根据项目ID查询公式信息
     */
    @Override
    public List<BusFormula> queryFormula(String projectId){
        return busFormulaDao.queryFormula(projectId);
    }

    /**
     * 根据公式ID和项目ID查询变量
     */
    @Override
    public List<VariableAndParam> queryVariable(String projectId, String formulaId){
        return busVariableDao.queryVariable(projectId,formulaId);
    }

    /**
     * 查询参数来源
     */
    @Override
    public List<SysSource> querySource(){
        return sysSourceDao.querySourceInfo();
    }

    /**
     * 增加变量信息
     */
    @Override
    public int addVariable(BusVariable busVariable){
        busVariable.setId(UuidUtil.create32());
        return busVariableDao.insertSelective(busVariable);
    }

    /**
     * 删除变量信息
     */
    @Override
    public int deleteVariable(BusVariable busVariable){
        return busVariableDao.deleteByPrimaryKey(busVariable);
    }

    /**
     * 修改变量信息
     */
    @Override
    public int updateVariable(BusVariable busVariable){
        return busVariableDao.updateByPrimaryKeySelective(busVariable);
    }

    /**
     * 根据项目ID和参数来源查询二级参数信息
     */
    @Override
    public List<BusParamSecond> queryParamSecondByProId(String projectId, String sourceId){
        return busParamSecondDao.queryParamSecondByProId(projectId,sourceId);
    }
}

package org.rcisoft.business.equipment.report.service.Impl;

import org.rcisoft.business.equipment.report.service.FormulaOperationService;
import org.rcisoft.dao.BusFormulaDao;
import org.rcisoft.entity.BusFormula;
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

    /**
     * 根据项目ID查询公式信息
     */
    @Override
    public List<BusFormula> queryFormula(String projectId){
        return busFormulaDao.queryFormula(projectId);
    }
}

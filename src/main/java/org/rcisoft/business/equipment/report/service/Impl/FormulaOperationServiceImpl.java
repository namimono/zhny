package org.rcisoft.business.equipment.report.service.Impl;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rcisoft.base.util.ExcelUtil;
import org.rcisoft.base.util.FormulaUtil;
import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.equipment.report.entity.*;
import org.rcisoft.business.equipment.report.service.FormulaOperationService;
import org.rcisoft.business.equipment.report.dao.FormulaDao;
import org.rcisoft.dao.*;
import org.rcisoft.entity.BusFormula;
import org.rcisoft.entity.BusVariable;
import org.rcisoft.entity.SysData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author 土豆儿
 * @date 2019/3/12 16:37
 **/
@Service
public class FormulaOperationServiceImpl implements FormulaOperationService {

    @Autowired
    private FormulaDao formulaDao;
    @Autowired
    private BusFormulaDao busFormulaDao;
    @Autowired
    private BusVariableDao busVariableDao;
    @Autowired
    private SysSourceDao sysSourceDao;
    @Autowired
    private BusParamSecondDao busParamSecondDao;
    @Autowired
    private SysDataDao sysDataDao;

    @Transactional
    @Override
    public int editFormulaAndVariable(FormulaParams formulaParams) {
        BusFormula busFormula = formulaParams.getBusFormula();
        List<BusVariable> variableList = formulaParams.getVariableList();
        String delIds = formulaParams.getDeleteVariableIds();
        String projectId = formulaParams.getProjectId();
        // 数据库操作结果
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        // 日期
        Date date = new Date();
        // 操作的标志，0：新增，1：更新
        int saveOrUpdate = 0;
        // 删除的id集合
        String[] delArrays = null;
        // 获取公式id
        String formulaId = busFormula.getId();
        // 公式id为空，是新增，不为空，是更新
        if (StringUtils.isEmpty(formulaId)) {
            formulaId = UuidUtil.create32();
            busFormula.setId(formulaId);
            busFormula.setProjectId(projectId);
            busFormula.setCreateTime(date);
        } else {
            saveOrUpdate = 1;
        }
        // 循环参数集合，如果id为空，放入待新增的list；如果不为空，放入待更新的设备
        List<BusVariable> insertList = new ArrayList<>();
        List<BusVariable> updateList = new ArrayList<>();
        for (BusVariable busVariable : variableList) {
            if (StringUtils.isEmpty(busVariable.getId())) {
                // 需要新增
                busVariable.setId(UuidUtil.create32());
                busVariable.setFormulaId(formulaId);
                busVariable.setProjectId(projectId);
                busVariable.setCreateTime(date);
                insertList.add(busVariable);
            } else {
                // 需要更新
                updateList.add(busVariable);
            }
        }
        // 删除
        if (StringUtils.isNotEmpty(delIds)) {
            delArrays = delIds.split(",");
        }
        /** 数据库的操作 */
        // 公式表
        if (saveOrUpdate == 0) {
            // 新增
            a = busFormulaDao.insertSelective(busFormula);
        } else {
            // 更新
            b = busFormulaDao.updateByPrimaryKey(busFormula);
        }
        // 参数表
        if (insertList.size() > 0) {
            c = busVariableDao.batchSave(insertList);
        }
        if (updateList.size() > 0) {
            d = busVariableDao.batchUpdate(updateList);
        }
        // 删除参数表
        if (delArrays != null) {
            e = busVariableDao.batchDelete(delArrays);
        }
        return a + b + c + d + e;
    }

    @Transactional
    @Override
    public int deleteFormula(String formulaId) {
        // 删除公式表
        int result = busFormulaDao.deleteByPrimaryKey(formulaId);
        // 删除变量表
        BusVariable busVariable = new BusVariable();
        busVariable.setFormulaId(formulaId);
        busVariableDao.delete(busVariable);
        return result;
    }

    @Override
    public List<FormulaAndVariables> queryFormulaAndVariale(String projectId) {
        // 查询所有公式
        List<FormulaAndVariables> formulaList = formulaDao.queryFormulasByProjectId(projectId);
        // 查询所有变量
        Example example = new Example(BusVariable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        example.setOrderByClause("create_time desc");
        List<BusVariable> variableList = busVariableDao.selectByExample(example);
        // 循环公式集合，将变量放入对应的公式
        formulaList.forEach(formulaAndVariables -> {
            String id = formulaAndVariables.getId();
            List<BusVariable> list = formulaAndVariables.getVariableList();
            variableList.forEach(busVariable -> {
                String formulaId = busVariable.getFormulaId();
                if (StringUtils.equals(id, formulaId)) {
                    // 如果公式id一致，放入
                    list.add(busVariable);
                }
            });
        });
        return formulaList;
    }

    @Override
    public List<EchartResult> queryData(String projectId, String date) {
        Calendar calendar = Calendar.getInstance();
        FelEngine felEngineImpl = new FelEngineImpl();
        // 新建返回值
        List<EchartResult> resultList = new ArrayList<>();
        // 查询公式
        List<FormulaEntity> formulaList = this.queryFormulaEntity(projectId);
        // 查询sys_data数据
        List<SysData> dataList = sysDataDao.querySysDataFormat(date, projectId);
        // 循环公式集合
        formulaList.forEach(formulaEntity -> {
            // 新建单个返回值
            EchartResult echartResult = new EchartResult();
            // 公式名称
            echartResult.setName(formulaEntity.getName());
            // 数据集合
            Double[] data = echartResult.getData();
            // 公式
            String formula = formulaEntity.getFormula();
            // 变量集合
            List<VariableParam> variableParamList = formulaEntity.getVariableParamList();
            // 变量排序
            Collections.sort(variableParamList, (o1, o2) -> {
                String o1V = o1.getVariable();
                String o2V = o2.getVariable();
                if (o1V.length() > o2V.length()) {
                    return -1;
                } else {
                    return 1;
                }
            });
            // 处理数据
            dataList.forEach(sysData -> {
                String value = null;
                calendar.setTime(sysData.getCreateTime());
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String json = sysData.getJson();
                // 从公式中取数据
                // 替换数据之后的公式计算式
                String felFormula = new String(formula);
                // 循环变量
                for (VariableParam variableParam : variableParamList) {
                    // 变量
                    String variable = variableParam.getVariable();
                    // 一级
                    String first = variableParam.getCodingFirst();
                    // 二级
                    String second = variableParam.getCodingSecond();
                    // 变量的值
                    String variableValue = FormulaUtil.getValueFromJson(first, second, json);
                    // 替换公式中的变量
                    if (variableValue != null) {
                        felFormula = felFormula.replaceAll(variable, variableValue);
                    }
                }
                // 计算结果
                value = FormulaUtil.calculate(felFormula, felEngineImpl);
                // 放入结果集
                if (value != null) {
                    data[hour] = Double.parseDouble(value);
                }
            });
            resultList.add(echartResult);
        });
        return resultList;
    }

    @Override
    public void downloadData(HttpServletRequest request, HttpServletResponse response, String projectId, String date) {
        List<EchartResult> echartList = this.queryData(projectId, date);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        // 添加表头
        Row row = sheet.createRow(0);
        for (int i = 1; i < 25; i++) {
            row.createCell(i, CellType.STRING).setCellValue(i + "时");
        }
        // 添加数据
        for (int i = 0; i < echartList.size(); i++) {
            Row dataRow = sheet.createRow(1 + i);
            EchartResult echartResult = echartList.get(i);
            dataRow.createCell(0, CellType.STRING).setCellValue(echartResult.getName());
            Double[] data = echartResult.getData();
            for (int j = 0; j < data.length; j++) {
                dataRow.createCell(j + 1, CellType.STRING).setCellValue(data[j]);
            }
        }
        ExcelUtil.downloadExcel(request, response, date + "公式数据", workbook);
    }

    private List<FormulaEntity> queryFormulaEntity(String projectId) {
        // 查询所有公式
        List<FormulaEntity> formulaList = formulaDao.queryFormulaEntity(projectId);
        // 查询公式变量
        List<VariableParam> variableParamList = formulaDao.queryVariableParam(projectId);

        formulaList.forEach(formulaEntity -> {
            String id = formulaEntity.getId();
            List<VariableParam> variableList = formulaEntity.getVariableParamList();
            variableParamList.forEach(variableParam -> {
                String formulaId = variableParam.getFormulaId();
                if (StringUtils.equals(id, formulaId)) {
                    variableList.add(variableParam);
                }
            });
        });

        return formulaList;
    }
}

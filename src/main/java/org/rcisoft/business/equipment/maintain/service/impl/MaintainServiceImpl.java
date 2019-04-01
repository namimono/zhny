package org.rcisoft.business.equipment.maintain.service.impl;

import org.rcisoft.base.util.UuidUtil;
import org.rcisoft.business.equipment.maintain.dao.MaintainDao;
import org.rcisoft.business.equipment.maintain.entity.MaintainPlanResult;
import org.rcisoft.business.equipment.maintain.entity.ScheduleResult;
import org.rcisoft.business.equipment.maintain.service.MaintainService;
import org.rcisoft.dao.BusMaintenanceDao;
import org.rcisoft.entity.BusMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JiChao on 2019/4/1.
 * 设备维护--养护计划
 */
@Service
public class MaintainServiceImpl implements MaintainService {

    @Autowired
    MaintainDao maintainDao;
    @Autowired
    BusMaintenanceDao busMaintenanceDao;

    @Override
    public List<ScheduleResult> querySchedule(String projectId, Integer year) {
        return maintainDao.querySchedule(projectId, year);
    }

    @Override
    public List<MaintainPlanResult> queryPlan(String projectId, Integer year, Integer month, Integer day) {
        return maintainDao.queryPlan(projectId, year + "-" + month + "-" + day);
    }

    @Override
    public BusMaintenance queryMaintenance(String id) {
        return busMaintenanceDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insertMaintenance(BusMaintenance busMaintenance) {
        busMaintenance.setId(UuidUtil.create32());
        return busMaintenanceDao.insert(busMaintenance);
    }

    @Override
    public Integer updateMaintenance(BusMaintenance busMaintenance) {
        return busMaintenanceDao.updateByPrimaryKey(busMaintenance);
    }

    @Override
    public Integer deleteMaintenance(String id) {
        return busMaintenanceDao.deleteByPrimaryKey(id);
    }
}

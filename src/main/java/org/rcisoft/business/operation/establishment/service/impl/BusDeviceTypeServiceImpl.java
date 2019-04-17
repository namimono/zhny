package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.service.BusDeviceTypeService;
import org.rcisoft.dao.BusDeviceTypeDao;
import org.rcisoft.entity.BusDeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/4/17
 */
@Service
public class BusDeviceTypeServiceImpl implements BusDeviceTypeService {
    @Autowired
    private BusDeviceTypeDao busDeviceTypeDao;

    @Override
    public List<BusDeviceType> listBusDeviceType() {
        return busDeviceTypeDao.selectAll();
    }
}

package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.service.BusDeviceService;
import org.rcisoft.dao.BusDeviceDao;
import org.rcisoft.entity.BusDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Service
public class BusDeviceServiceImpl implements BusDeviceService {
    @Autowired
    private BusDeviceDao busDeviceDao;


    @Override
    public List<BusDevice> listBusDevice(BusDevice busDevice) {
        return busDeviceDao.select(busDevice);
    }
}

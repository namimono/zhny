package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.service.BusTypeSecondService;
import org.rcisoft.dao.BusTypeSecondDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Service
public class BusTypeSecondServiceImpl implements BusTypeSecondService {
    @Autowired
    private BusTypeSecondDao busTypeSecondDao;


    @Override
    public List<BusTypeSecond> listBusTypeSecondByFirstId(BusTypeSecond busTypeSecond) {
        return busTypeSecondDao.select(busTypeSecond);
    }
}

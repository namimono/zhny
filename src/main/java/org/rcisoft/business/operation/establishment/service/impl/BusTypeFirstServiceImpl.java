package org.rcisoft.business.operation.establishment.service.impl;

import org.rcisoft.business.operation.establishment.service.BusTypeFirstService;
import org.rcisoft.dao.BusTypeFirstDao;
import org.rcisoft.entity.BusTypeFirst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/3/19
 */
@Service
public class BusTypeFirstServiceImpl implements BusTypeFirstService {

    @Autowired
    private BusTypeFirstDao busTypeFirstDao;

    @Override
    public List<BusTypeFirst> listBusTypeFirst() {
        return busTypeFirstDao.selectAll();
    }
}

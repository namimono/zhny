package org.rcisoft.business.whole.right.service.Impl;

import org.rcisoft.business.whole.right.dao.BusPushRecordDao;
import org.rcisoft.business.whole.right.service.BusPushRecordService;
import org.rcisoft.entity.BusPushRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:26 2019/3/13
 */
@Service
public class BusPushRecordServiceImpl implements BusPushRecordService {

    @Autowired
    BusPushRecordDao busPushRecordDao;

    /**
     * 查询消息推送时间及内容
     * @return
     */
    @Override
    public BusPushRecord queryContent() {
        return busPushRecordDao.queryContent();
    }
}

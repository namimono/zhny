package org.rcisoft.business.whole.home.service.Impl;

import org.apache.tools.ant.Project;
import org.rcisoft.business.whole.home.dao.HomeDao;
import org.rcisoft.business.whole.home.entity.ProjectDetail;
import org.rcisoft.business.whole.home.entity.ProjectHome;
import org.rcisoft.business.whole.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 9:34 2019/4/10
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeDao homeDao;

    @Override
    public List<ProjectHome> queryProjectHome(int year) {
        List<ProjectHome> listResult = new ArrayList<>();
        List<Map<String,Object>> energyStatics = homeDao.queryEnergyStatics(year);
        List<Map<String,Object>> carbonYear = homeDao.queryCarbonYear(year);
        List<Map<String,Object>> status = homeDao.queryStatus();
        List<ProjectHome> projectDetail = homeDao.queryProjectDetail();
        for(ProjectHome pd:projectDetail){
            String Id = pd.getId();
            for(Map es:energyStatics){
                if (es.get("proId").equals(Id)){
                    pd.setMoneyGas((BigDecimal) es.get("moneyGas"));
                    pd.setMoneyElec((BigDecimal) es.get("moneyElec"));
                    pd.setMoneyWater((BigDecimal) es.get("moneyWater"));
                    BigDecimal moneyGas = (BigDecimal) es.get("moneyGas");
                    BigDecimal moneyElec = (BigDecimal) es.get("moneyElec");
                    BigDecimal moneyWater = (BigDecimal) es.get("moneyWater");
                    BigDecimal sum = moneyGas.add(moneyElec).add(moneyWater);
                    pd.setMoneySum(sum);
                }
            }
            for (Map cy:carbonYear){
                if (cy.get("proId").equals(Id)){
                    pd.setCarbon((BigDecimal)cy.get("carbon"));
                }
            }
            for(Map st:status){
                if (st.get("proId").equals(Id)){
                    Long count = (Long) st.get("count");
                    if(count > 5) {
                        pd.setStatus(1);
                    }else {
                        pd.setStatus(0);
                    }
                }
            }
            listResult.add(pd);
        }
        return listResult;
    }
}

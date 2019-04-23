package org.rcisoft.business.whole.home.service.Impl;

import org.rcisoft.base.jwt.JwtTokenUtil;
import org.rcisoft.base.redis.RedisService;
import org.rcisoft.business.whole.home.dao.HomeDao;
import org.rcisoft.business.whole.home.entity.ProjectHome;
import org.rcisoft.business.whole.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
    @Autowired
    private RedisService redisService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    private final static String count = "-count";

    @Override
    public List<ProjectHome> queryProjectHome(HttpServletRequest request) {
        String userId = jwtTokenUtil.getClaimsFromToken(request.getHeader(tokenHeader).substring(tokenHead.length())).get("userid", String.class);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<ProjectHome> listResult = new ArrayList<>();
        List<Map<String,Object>> energyStatics = homeDao.queryEnergyStatics(year, month, day);
        List<Map<String,Object>> carbonYear = homeDao.queryCarbonYear(year);
        List<Map<String,Object>> status = homeDao.queryStatus();
        List<ProjectHome> projectDetail = homeDao.queryProjectDetail(userId);
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
                    if(count > 0) {
                        pd.setStatus(1);
                    }else {
                        pd.setStatus(0);
                    }
                }
            }
            /** 判断通信状态 */
            // 是否接收数据
            Integer receive = pd.getReceive();
            // 接收数据才会判断通信状态
            if (receive == 1) {
                // 得到项目所属网关号
                String phones = pd.getPhones();
                String[] phoneArray = phones.split(",");
                // 网关的数量
                int length = phoneArray.length;
                for (String phone: phoneArray) {
                    // 用网关号从redis中取得状态
                    int i = this.checkCommunicate(phone + this.count);
                    length = length - i;
                }
                // 判断length，如果所有网关都有错误，length=0
                if (length == 0) {
                    pd.setCommunicationStatus(1);
                }
            }
            listResult.add(pd);
        }
        return listResult;
    }

    /**
     * 从缓存查询项目通讯是否有故障
     * @param phone
     * @return
     */
    private int checkCommunicate(String phone) {
        String s = redisService.get(phone);
        if (s != null) {
            int count = Integer.valueOf(s);
            if (count > 5) {
                return 1; // 计数超过5次，认为该网关通讯故障
            } else {
                return 0; // 小于5次，认为没有问题
            }
        } else {
            return 1; // 如果没有值，也认为故障
        }
    }
}

package org.rcisoft.business.equipment.maintain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by JiChao on 2019/4/1.
 * 养护日程 返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScheduleResult {

    /**
     * 日期
     * 年月日
     */
    private Date time;

    /**
     * 故障次数
     */
    private Integer count;

}

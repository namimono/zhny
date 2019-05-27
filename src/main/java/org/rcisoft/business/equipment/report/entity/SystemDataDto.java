package org.rcisoft.business.equipment.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author GaoLiwei
 * @date 2019/5/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemDataDto {

    /**
     * 项目Id
     */
    private String projectId;


    /**
     * 选择日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    private Date date;

    /**
     * code参数
     */
    private List<FirstCodeAndSecondCode> firstCodeAndSecondCodeList;

}

package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/4/9.
 * 气候自适应参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClimateParam {

    /**
     * 一级、二级coding
     */
    private List<CodingParam> codingList = new ArrayList<>();

    /**
     * 年月日
     */
    private String time;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * excel表头：最优供水温度 + 自定义表头名称
     */
    private List<String> titleList = new ArrayList<>();

}

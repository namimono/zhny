package org.rcisoft.business.energy.overview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/3/19.
 * 能耗拆分返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EnergySplitResult {

    /**
     * 数据
     */
    private List<EnergySplit> list = new ArrayList<>();

    /**
     * 设备标题
     */
    private List<String> title = new ArrayList<>();

}

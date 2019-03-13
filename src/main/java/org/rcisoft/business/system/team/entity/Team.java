package org.rcisoft.business.system.team.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rcisoft.entity.BusTeam;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/3/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team extends BusTeam {

    /**
     * 团队负责人姓名
     */
    private String principalName;

}

package org.rcisoft.business.whole.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeResult {

    /**
     * 角色
     */
    private Integer type;

    private List<ProjectHome> projectList = new ArrayList<>();
}

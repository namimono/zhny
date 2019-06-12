package org.rcisoft.business.system.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchParams {

    private List<ParamFirstContainSecond> batchList = new ArrayList<>();

    private String paramFirstIds;

    private String paramSecondIds;

    private String deviceId;

}

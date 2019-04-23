package org.rcisoft.business.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiChao on 2019/4/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FirstParam {

    private String paramFirstId;

    private String paramFirstName;

    private String paramFirstCode;

    @JsonIgnore
    private String deviceId;

    private List<SecondParam> secondList = new ArrayList<>();

}

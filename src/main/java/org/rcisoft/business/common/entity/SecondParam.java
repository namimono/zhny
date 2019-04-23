package org.rcisoft.business.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/4/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SecondParam {

    private String paramSecondId;

    private String paramSecondName;

    private String paramSecondCode;

    @JsonIgnore
    private String paramFirstId;

}

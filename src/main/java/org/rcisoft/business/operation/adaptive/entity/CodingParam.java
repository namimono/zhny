package org.rcisoft.business.operation.adaptive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by JiChao on 2019/4/9.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CodingParam {

    private String title, codingFirst, codingSecond;

}

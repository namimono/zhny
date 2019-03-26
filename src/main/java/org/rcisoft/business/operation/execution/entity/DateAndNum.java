package org.rcisoft.business.operation.execution.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author GaoLiwei
 * @date 2019/3/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateAndNum {

    /**
     * 时间，格式 yyyy-MM-dd
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    Date date;

    /**
     *  记录数量
     */
    Integer recordNum;
}

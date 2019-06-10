package org.rcisoft.business.management.distribution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyCost {

    private String projectId;

    private BigDecimal moneyWater, moneyElec, moneyGas;

}

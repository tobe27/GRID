package model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Data
public class HomeStatisticsInfo implements Serializable {
    private static final long serialVersionUID = -709831097741968393L;
    private Long orgCode;

    private Integer orgLevel;

    private Long peopleNum;

    private BigDecimal amount;

    private Integer amountType;

    private String time;

    private Integer timeType;

}
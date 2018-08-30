package model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class HomeWarnInfo implements Serializable {
    private static final long serialVersionUID = 2064203283919450647L;
    private Long orgCode;

    private Integer orgLevel;

    private BigDecimal amount;

    private Integer amountType;

    private String message;

    private Long time;
}
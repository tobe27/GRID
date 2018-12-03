package model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Accessors(chain = true)
@Data
public class FinanceInfo implements Serializable {
    private static final long serialVersionUID = 6404575356551109616L;
    private Long financeId;

    private String idNumber;

    private String industry;

    private String career;

    private String companyName;

    private String companyAddress;

    private Integer workYear;

    private Integer houseNumber;

    private Double housePrice;

    private Integer carNumber;

    private Double carPrice;

    private Double yearSalary;

    private Integer shopNumber;

    private Double shopPrice;

    private Integer equipmentNumber;

    private Double equipmentPrice;

    private Double yearSurplus;

    private Double landAcreage;

    private Integer greenhouseNumber;

    private String breedType;

    private Integer breedNumber;

    private Double companyEarnings;

    private String other;

    private Long createdAt;

    private Long updatedAt;

    private String businessLicense;

    private String isFarmIncome;
    private String isBusinessIncome;

}
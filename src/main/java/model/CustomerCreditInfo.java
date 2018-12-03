package model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerCreditInfo implements Serializable{
	private static final long serialVersionUID = 5736254186882681499L;
	private Long id;
    private String idNumber;
    private BigDecimal incidenceOfLetter;
    private BigDecimal housingMortgageDebt;
    private BigDecimal businessLoanDebt;
    private BigDecimal consumerLoanDebt;
    private BigDecimal housingMortgageRemainder;
    private BigDecimal businessLoanRemainder;
    private BigDecimal consumerLoanRemainder;
    private Integer overdueRecord;
    private BigDecimal pastDue;
    private String basicIdentity;
    private String address1;
    private String address2;
    private String address3;
    private String mate;
    private String neighborhoodRelation;
    private Integer haveBadHabits;
    private Integer haveBadCredit;
    private Integer isDishonest;
    private Long createdAt;
    private Long updatedAt;
}
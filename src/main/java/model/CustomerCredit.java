package model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.test.context.TestPropertySource;

import lombok.ToString;
@ToString
public class CustomerCredit implements Serializable{
   
	private static final long serialVersionUID = -743152357709294184L;

	private Long id;

    private String idNumber;

    private BigDecimal preCreditLimit;

    private BigDecimal interestRate;

    private Long createdAt;

    private Long updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public BigDecimal getPreCreditLimit() {
        return preCreditLimit;
    }

    public void setPreCreditLimit(BigDecimal preCreditLimit) {
        this.preCreditLimit = preCreditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
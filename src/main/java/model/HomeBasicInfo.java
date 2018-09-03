package model;

import java.math.BigDecimal;

import lombok.ToString;
@ToString
public class HomeBasicInfo {
    private Long id;

    private Long familyNum;

    private Long peopleNum;

    private BigDecimal creditRatio;

    private Long creditNum;

    private Long useRatio;

    private Long useNum;

    private Long orgCode;

    private BigDecimal usePromote;

    private Long accountId;

    private Long craetAt;

    private Long updateAt;

    private BigDecimal deposit;

    private BigDecimal loan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFamilyNum() {
        return familyNum;
    }

    public void setFamilyNum(Long familyNum) {
        this.familyNum = familyNum;
    }

    public Long getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Long peopleNum) {
        this.peopleNum = peopleNum;
    }

    public BigDecimal getCreditRatio() {
        return creditRatio;
    }

    public void setCreditRatio(BigDecimal creditRatio) {
        this.creditRatio = creditRatio;
    }

    public Long getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(Long creditNum) {
        this.creditNum = creditNum;
    }

    public Long getUseRatio() {
        return useRatio;
    }

    public void setUseRatio(Long useRatio) {
        this.useRatio = useRatio;
    }

    public Long getUseNum() {
        return useNum;
    }

    public void setUseNum(Long useNum) {
        this.useNum = useNum;
    }

    public Long getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Long orgCode) {
        this.orgCode = orgCode;
    }

    public BigDecimal getUsePromote() {
        return usePromote;
    }

    public void setUsePromote(BigDecimal usePromote) {
        this.usePromote = usePromote;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCraetAt() {
        return craetAt;
    }

    public void setCraetAt(Long craetAt) {
        this.craetAt = craetAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getLoan() {
        return loan;
    }

    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }
}
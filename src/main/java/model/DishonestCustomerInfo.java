package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DishonestCustomerInfo implements Serializable {
    private static final long serialVersionUID = 2952636877575478687L;
    private Long id;

    private String performedName;

    private String cardNumber;

    private String sex;

    private Integer age;

    private String areaName;

    private String courtName;

    private String gistId;

    private String registerDate;

    private String caseCode;

    private String gistInstitution;

    private String duty;

    private String performance;

    private String concreteReason;

    private String type;

    private Long publishedAt;

    private Long createdAt;

    private Long updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerformedName() {
        return performedName;
    }

    public void setPerformedName(String performedName) {
        this.performedName = performedName == null ? null : performedName.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge(Object age) {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName == null ? null : courtName.trim();
    }

    public String getGistId() {
        return gistId;
    }

    public void setGistId(String gistId) {
        this.gistId = gistId == null ? null : gistId.trim();
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate == null ? null : registerDate.trim();
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode == null ? null : caseCode.trim();
    }

    public String getGistInstitution() {
        return gistInstitution;
    }

    public void setGistInstitution(String gistInstitution) {
        this.gistInstitution = gistInstitution == null ? null : gistInstitution.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance == null ? null : performance.trim();
    }

    public String getConcreteReason() {
        return concreteReason;
    }

    public void setConcreteReason(String concreteReason) {
        this.concreteReason = concreteReason == null ? null : concreteReason.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Long publishedAt) {
        this.publishedAt = publishedAt;
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
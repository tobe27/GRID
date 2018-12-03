package model;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class CustomerPoorInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5887801810514531946L;

	private Integer id;

    private String personCode;

    private String idNumber;

    private String personCount;

    private String internalStudent;

    private String healthCondition;

    private String laborSkills;

    private String workingConditions;

    private String workingTime;

    private String criticalIllnessInsurance;

    private String overcomePoverty;

    private String overcomePovertyYear;

    private String attribute;

    private String reason;

    private String dangerousBuilding;

    private String safetyWater;

    private String dysdipsia;

    private String incomeAvg;

    private String phone;

    private String writeIme;

    private String status;

    private Long createdAt;

    private Long updateAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode == null ? null : personCode.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount == null ? null : personCount.trim();
    }

    public String getInternalStudent() {
        return internalStudent;
    }

    public void setInternalStudent(String internalStudent) {
        this.internalStudent = internalStudent == null ? null : internalStudent.trim();
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition == null ? null : healthCondition.trim();
    }

    public String getLaborSkills() {
        return laborSkills;
    }

    public void setLaborSkills(String laborSkills) {
        this.laborSkills = laborSkills == null ? null : laborSkills.trim();
    }

    public String getWorkingConditions() {
        return workingConditions;
    }

    public void setWorkingConditions(String workingConditions) {
        this.workingConditions = workingConditions == null ? null : workingConditions.trim();
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime == null ? null : workingTime.trim();
    }

    public String getCriticalIllnessInsurance() {
        return criticalIllnessInsurance;
    }

    public void setCriticalIllnessInsurance(String criticalIllnessInsurance) {
        this.criticalIllnessInsurance = criticalIllnessInsurance == null ? null : criticalIllnessInsurance.trim();
    }

    public String getOvercomePoverty() {
        return overcomePoverty;
    }

    public void setOvercomePoverty(String overcomePoverty) {
        this.overcomePoverty = overcomePoverty == null ? null : overcomePoverty.trim();
    }

    public String getOvercomePovertyYear() {
        return overcomePovertyYear;
    }

    public void setOvercomePovertyYear(String overcomePovertyYear) {
        this.overcomePovertyYear = overcomePovertyYear == null ? null : overcomePovertyYear.trim();
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute == null ? null : attribute.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getDangerousBuilding() {
        return dangerousBuilding;
    }

    public void setDangerousBuilding(String dangerousBuilding) {
        this.dangerousBuilding = dangerousBuilding == null ? null : dangerousBuilding.trim();
    }

    public String getSafetyWater() {
        return safetyWater;
    }

    public void setSafetyWater(String safetyWater) {
        this.safetyWater = safetyWater == null ? null : safetyWater.trim();
    }

    public String getDysdipsia() {
        return dysdipsia;
    }

    public void setDysdipsia(String dysdipsia) {
        this.dysdipsia = dysdipsia == null ? null : dysdipsia.trim();
    }

    public String getIncomeAvg() {
        return incomeAvg;
    }

    public void setIncomeAvg(String incomeAvg) {
        this.incomeAvg = incomeAvg == null ? null : incomeAvg.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWriteIme() {
        return writeIme;
    }

    public void setWriteIme(String writeIme) {
        this.writeIme = writeIme == null ? null : writeIme.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }
}
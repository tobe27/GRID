package model;

import lombok.Data;

import java.io.Serializable;
@Data
public class FamilyInfo implements Serializable {
    private static final long serialVersionUID = 5558088728223503845L;
    private Long familyId;

    private Long householdId;

    private Integer population;

    private String address;

    private Integer postcode;

    private String localCredit;

    private String isHarmony;

    private String socialEvaluation;

    private String isSelfEntity;

    private String isOweTax;

    private String produceCapacity;

    private String produceScene;

    private Long accountId;

    private String insuranceType;

    private Long createdAt;

    private Long updatedAt;

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Long getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Long householdId) {
        this.householdId = householdId;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getLocalCredit() {
        return localCredit;
    }

    public void setLocalCredit(String localCredit) {
        this.localCredit = localCredit == null ? null : localCredit.trim();
    }

    public String getIsHarmony() {
        return isHarmony;
    }

    public void setIsHarmony(String isHarmony) {
        this.isHarmony = isHarmony == null ? null : isHarmony.trim();
    }

    public String getSocialEvaluation() {
        return socialEvaluation;
    }

    public void setSocialEvaluation(String socialEvaluation) {
        this.socialEvaluation = socialEvaluation == null ? null : socialEvaluation.trim();
    }

    public String getIsSelfEntity() {
        return isSelfEntity;
    }

    public void setIsSelfEntity(String isSelfEntity) {
        this.isSelfEntity = isSelfEntity == null ? null : isSelfEntity.trim();
    }

    public String getIsOweTax() {
        return isOweTax;
    }

    public void setIsOweTax(String isOweTax) {
        this.isOweTax = isOweTax == null ? null : isOweTax.trim();
    }

    public String getProduceCapacity() {
        return produceCapacity;
    }

    public void setProduceCapacity(String produceCapacity) {
        this.produceCapacity = produceCapacity == null ? null : produceCapacity.trim();
    }

    public String getProduceScene() {
        return produceScene;
    }

    public void setProduceScene(String produceScene) {
        this.produceScene = produceScene == null ? null : produceScene.trim();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType == null ? null : insuranceType.trim();
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
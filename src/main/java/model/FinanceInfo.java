package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FinanceInfo implements Serializable {
    private static final long serialVersionUID = 6404575356551109616L;
    private Long financeId;

    private String idNumber;

    private Integer industry;

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

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career == null ? null : career.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public Integer getWorkYear() {
        return workYear;
    }

    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(Double housePrice) {
        this.housePrice = housePrice;
    }

    public Integer getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Integer carNumber) {
        this.carNumber = carNumber;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Double getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(Double yearSalary) {
        this.yearSalary = yearSalary;
    }

    public Integer getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(Integer shopNumber) {
        this.shopNumber = shopNumber;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Double getEquipmentPrice() {
        return equipmentPrice;
    }

    public void setEquipmentPrice(Double equipmentPrice) {
        this.equipmentPrice = equipmentPrice;
    }

    public Double getYearSurplus() {
        return yearSurplus;
    }

    public void setYearSurplus(Double yearSurplus) {
        this.yearSurplus = yearSurplus;
    }

    public Double getLandAcreage() {
        return landAcreage;
    }

    public void setLandAcreage(Double landAcreage) {
        this.landAcreage = landAcreage;
    }

    public Integer getGreenhouseNumber() {
        return greenhouseNumber;
    }

    public void setGreenhouseNumber(Integer greenhouseNumber) {
        this.greenhouseNumber = greenhouseNumber;
    }

    public String getBreedType() {
        return breedType;
    }

    public void setBreedType(String breedType) {
        this.breedType = breedType == null ? null : breedType.trim();
    }

    public Integer getBreedNumber() {
        return breedNumber;
    }

    public void setBreedNumber(Integer breedNumber) {
        this.breedNumber = breedNumber;
    }

    public Double getCompanyEarnings() {
        return companyEarnings;
    }

    public void setCompanyEarnings(Double companyEarnings) {
        this.companyEarnings = companyEarnings;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
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
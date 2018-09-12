package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResidentInfo implements Serializable {
    private static final long serialVersionUID = -1748337587589363382L;
    private Long residentId;

    private String residentName;

    private String sex;

    private String nation;

    private String birthdate;

    private String idNumber;

    private String address;

    private String householdType;

    private String relationship;

    private Long householdId;

    private String contact;

    private String isInList;

    private String remark;

    private Long createdAt;

    private Long updatedAt;

}
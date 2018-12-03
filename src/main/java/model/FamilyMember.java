package model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class FamilyMember {
    private Long memberId;

    private String memberName;

    private String relationship;

    private String nation;

    private String birthday;

    private String idType;

    private String idNumber;

    private String address;

    private String isHouseholdHead;

    private String contact;

    private String remark;

    private Integer status;

    private Long customerId;

    private Long createdAt;

    private Long updatedAt;

}
package model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class CustomerInfo implements Serializable {
    private static final long serialVersionUID = -1959845957466682661L;
    private Long customerId;

    private String customerName;

    private String sex;

    private String idType;  // 新增证件类型

    private String idNumber;

    private String maritalStatus;

    private String educationLevel;

    private String nation;

    private String birthday;

    private String nativePlace;

    private String phoneNumber;

    private String custodian;

    private String gridCode;

    private String householdId;

    private String nativeAddress;

    private String residenceAddress;

    private Long createdAt;

    private Long updatedAt;

    private Integer status;

    private String politicsStatus;

    private String livingSituation;

    private String physicalCondition;

    private String nationality;

    private String borrowerNature;

    private String postcode;

    private String isStockholder;

    private String isStaff;

    private String isOwner;

    private String isBuyHouse;

    private String cellName;

    private String cellAddress;


    private String career;  // 职业
    private String creditId;    // 信贷客户号
    private String workYear;   // 工作年限
    private String companyName; // 工作单位
    private String companyAddress;  // 单位地址
    private String isFarmer;    // 是否农户
    private String investigator1;   // 调查人A
    private String investigator2;   // 调查人B
    private String registrant;  // 登记人
    private String registerOrg; // 登记机构


    // 联查使用属性
    private Long accountId;
    private Long orgCode;
    private String gridName;

    private List<TagCustomer> tags;
}
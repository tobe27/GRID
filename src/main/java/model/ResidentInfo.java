package model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Data
@Accessors(chain = true)
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

    private String householdId;

    private String contact;

    private String isInList;

    private String remark;

    private String career;

    private Long createdAt;

    private Long updatedAt;

    private Integer status;

    private String gridCode;

    // 关联使用
    private String gridName;
    private Long accountId;
    private Long roleId;
    private Long orgCode;

}
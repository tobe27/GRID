package model;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
@Accessors(chain = true)
@Data
public class CustomerCreditDetail implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6255350313687669703L;

	private Long id;

    private String customerName;

    private String idNumber;

    private String gridCode;

    private String beginAt;

    private String endAt;

    private String comment;

    private Long createdAt;

    private Long updatedAt;

    private String approvalStatus;

    private String postil;

    private String attachFlag;

    private String customerId;

    private String sex;

    private String spouseName;

    private String spouseIdNumber;

    private String educationLevel;

    private String phoneNumber;

    private String memberCount;

    private String companyName;

    private String companyAddress;

    private String nativeAddress;

    private String residenceAddress;

    private String jnydName;

    private String jnydId;

    private String rental;

    private String deadline;

    private String rateType;

    private String yearRate;

    private String rateFloat;

    private String rateUpdateDate;

    private String disbursement;

    private String refundType;

    private String creditUseType;

    private String income;

    private String familyIncome;

    private String advisersFlag;

    private String advisersIncome;

    private String advisersExpense;

    private String advisersRental;

    private String advisersName;

    private String advisersComment;

    private String trueCustomerInfo;

    private String trueCustomerTalk;

    private String staffName;

    private String staffId;

    private String talkDate;

    private String gridName;

    private String accountName;

    private String accountId;

    private String orgName;

    private String orgCode;

    private String status;

    private String creditReport;

   
}
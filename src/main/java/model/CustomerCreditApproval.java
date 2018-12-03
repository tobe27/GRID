package model;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class CustomerCreditApproval implements Serializable{
   
	private static final long serialVersionUID = 4954448687339117478L;

	private Long id;

    private String idNumber;

    private String approvalOpinion;

    private String approvalName;

    private Long approvalAccountId;

    private String signatureurl;

    private Long createdAt;

    private Long updatedAt;

    private String approvalResult;

    private String approvalNode;
    private Long creditDetailId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion == null ? null : approvalOpinion.trim();
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName == null ? null : approvalName.trim();
    }

    public Long getApprovalAccountId() {
        return approvalAccountId;
    }

    public void setApprovalAccountId(Long approvalAccountId) {
        this.approvalAccountId = approvalAccountId;
    }

    public String getSignatureurl() {
        return signatureurl;
    }

    public void setSignatureurl(String signatureurl) {
        this.signatureurl = signatureurl == null ? null : signatureurl.trim();
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

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult == null ? null : approvalResult.trim();
    }

    public String getApprovalNode() {
        return approvalNode;
    }

    public void setApprovalNode(String approvalNode) {
        this.approvalNode = approvalNode == null ? null : approvalNode.trim();
    }

	public Long getCreditDetailId() {
		return creditDetailId;
	}

	public void setCreditDetailId(Long creditDetailId) {
		this.creditDetailId = creditDetailId;
	}
    
}
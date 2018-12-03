package model;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class GridInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5594298325268571229L;

	private Long id;

    private String gridCode;

    private String gridName;

    private Long orgCode;
    private String orgName; // 联查使用

    private Integer gridType;

    private String description;

    private Long accountId;

    private String assistManager;

    private String superviseManager;

    private String qrCode;

    private String gridMap;

    private Long createdAt;

    private Long updatedAt;
    
    private List<GridReview> listReview;
    private String deleteFlag;
    
    public List<GridReview> getListReview() {
		return listReview;
	}

	public void setListReview(List<GridReview> listReview) {
		this.listReview = listReview;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGridCode() {
        return gridCode;
    }

    public void setGridCode(String gridCode) {
        this.gridCode = gridCode == null ? null : gridCode.trim();
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName == null ? null : gridName.trim();
    }

    public Long getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Long orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getGridType() {
        return gridType;
    }

    public void setGridType(Integer gridType) {
        this.gridType = gridType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAssistManager() {
        return assistManager;
    }

    public void setAssistManager(String assistManager) {
        this.assistManager = assistManager == null ? null : assistManager.trim();
    }

    public String getSuperviseManager() {
        return superviseManager;
    }

    public void setSuperviseManager(String superviseManager) {
        this.superviseManager = superviseManager == null ? null : superviseManager.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public String getGridMap() {
        return gridMap;
    }

    public void setGridMap(String gridMap) {
        this.gridMap = gridMap == null ? null : gridMap.trim();
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
    
}
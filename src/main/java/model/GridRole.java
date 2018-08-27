package model;

import java.io.Serializable;
import java.util.List;

import lombok.ToString;

@ToString
public class GridRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4317171768880477614L;

	private Long roleId;

    private String roleName;

    private String roleScope;

    private String description;

    private Long createdAt;

    private Long updatedAt;
    
    private List<GridUser> userList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleScope() {
        return roleScope;
    }

    public void setRoleScope(String roleScope) {
        this.roleScope = roleScope == null ? null : roleScope.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

	public List<GridUser> getUserList() {
		return userList;
	}

	public void setUserList(List<GridUser> userList) {
		this.userList = userList;
	}

	
}
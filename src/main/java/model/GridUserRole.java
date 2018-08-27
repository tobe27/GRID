package model;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class GridUserRole implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6422109493269521345L;

	private Long id;

    private String corpCode;

    private Long accountId;

    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode == null ? null : corpCode.trim();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
package model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
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
    
    private List<GridPermission> permissionList;

    // 关联查询使用
    private Long orgCode;
	
}
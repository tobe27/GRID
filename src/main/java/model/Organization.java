package model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Data
public class Organization implements Serializable {
    private static final long serialVersionUID = 6760125305786077678L;

    private Long orgId;

    private Long orgCode;

    private String orgName;

    private String corpCode;

    private Long preOrgId;

    private Integer relationType;

    private Integer status;

    private Integer virtualOrgFlag;

    private String description;

    private Integer type;

    private Integer orgLevel;

    private Long createdAt;

    private Long updatedAt;

}
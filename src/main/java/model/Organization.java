package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Organization implements Serializable {
    private static final long serialVersionUID = 6760125305786077678L;
    private Long orgCode;

    private String orgName;

    private String corpCode;

    private String preOrgCode;

    private Integer relationType;

    private Integer status;

    private Integer virtualOrgFlag;

    private String description;

    private Integer type;

    private Integer orgLevel;

    private Long createdAt;

    private Long updatedAt;

}
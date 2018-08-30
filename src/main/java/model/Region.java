package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Region implements Serializable {
    private static final long serialVersionUID = 4338608137032662546L;
    private Long regionCode;

    private String regionName;

    private Long preRegionCode;

    private Integer regionLevel;

    private String description;

    private Long createdAt;

    private Long updatedAt;

}
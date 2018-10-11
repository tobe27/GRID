package model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
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
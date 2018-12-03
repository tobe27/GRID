package model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 网格评议员
 * @author Created by L.C.Y on 2018-11-23
 */
@Accessors(chain = true)
@Data
public class GridSenator implements Serializable {
    private static final long serialVersionUID = -135950985373865424L;

    private Long senatorId;

    private String senatorName;

    private String idNumber;

    private String phoneNumber;

    private String duty;

    private String address;

    private String description;

    private String isFormal;

    private Integer type;

    private Long createdAt;

    private Long updatedAt;

    // 关联使用
    private String gridCode;
}
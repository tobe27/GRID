package model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Data
@Accessors(chain = true)
public class TagCustomer implements Serializable {
    private static final long serialVersionUID = 4833108066434850312L;
    private Long id;

    private Long tagId;

    private String tagName;

    private String idNumber;

    private String reason;

    private Long createdAt;

    private Long updatedAt;

}
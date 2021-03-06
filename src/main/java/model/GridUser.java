package model;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author Created by L.C.Y on 2018-9-20
 */
@Data
public class GridUser implements Serializable {
    private static final long serialVersionUID = -3644728745266251434L;

    private Long accountId;

    private String accountName;

    private String password;

    private String realName;

    private String idNumber;

    private Integer sex;

    private Long phoneNumber;

    private String email;

    private String avatar;

    private String qrCode;

    private Integer status;

    private String corpCode;

    private String corpName;

    private Long orgCode;

    private String orgName;

    private Long createdAt;

    private Long updatedAt;

    private List<GridRole> roles;
}
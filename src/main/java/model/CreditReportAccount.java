package model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class CreditReportAccount implements Serializable{
	private static final long serialVersionUID = -8984213196172706619L;

	private Long id;

    private String userName;

    private String password;

    private String orgName;

    private String orgCode;

    private String status;

    private String comment;

    private String remind;

    private Long createdAt;

    private Long updatedAt;
    private String type;

}
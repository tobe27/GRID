package model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BusinessProduct implements Serializable {
	private static final long serialVersionUID = 4848335605398813777L;
	private Long id;
	private String idNumber;
	private String productCode;
	private String accountName;
	private String cardNumber;
	private Double rate;
	private Double money;
	private Long createdAt;
	private Long updatedAt;
	private String productName;
}

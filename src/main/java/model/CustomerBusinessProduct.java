package model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerBusinessProduct implements Serializable {
	private static final long serialVersionUID = 2211163329817457921L;
	private Long id;
	private String idNumber;
	private String productCode;
	private String accountName;
	private String cardNumber;
	private BigDecimal rate;
	private BigDecimal money;
	private Long createdAt;
	private Long updatedAt;
	
	private String productName;
}

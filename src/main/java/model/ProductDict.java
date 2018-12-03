package model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDict implements Serializable {
	private static final long serialVersionUID = -7233291752507624126L;
	private Long id;
	private String name;
	private String code;
	private Integer state;
	private BigDecimal rate;
	private String explain;
	private Long createdAt;
	private Long updatedAt;
}

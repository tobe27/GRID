package model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductDict implements Serializable {
	private static final long serialVersionUID = -6144999730747364354L;
	private Long id;
	private String name;
	private String code;
	private Integer state;
	private Long createdAt;
	private Long updatedAt;
}

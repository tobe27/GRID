package model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerBusiness implements Serializable {
	private static final long serialVersionUID = 7783029672569722012L;
	private Long id;
	private String idNumber;
	private BigDecimal depositTotal;
	private BigDecimal depositMonth;
	private BigDecimal depositSeason;
	private BigDecimal depositYear;
	private BigDecimal depositTimePoit;
	private BigDecimal currentTotal;
	private BigDecimal currentMonth;
	private BigDecimal currentSeason;
	private BigDecimal currentYear;
	private BigDecimal currentTimePoit;
	private BigDecimal fixedTotal;
	private BigDecimal fixedMonth;
	private BigDecimal fixedSeason;
	private BigDecimal fixedYear;
	private BigDecimal fixedTimePoit;
	private Long createdAt;
	private Long updatedAt;
}

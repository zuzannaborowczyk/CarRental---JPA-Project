package com.capgemini.types;

import java.util.Date;





import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoanTO {

	private Long id;

	private Date dateFrom;

	private Date dateTo;

	private int loanPrice;

	private Long officeFromId;

	private Long officeToId;

	private Long carId;

	private Long customerId;

}

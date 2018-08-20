package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.LoanEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.LoanTO;

public class LoanMapper {

	public static LoanEntity mapToEntity(LoanTO newLoan, CustomerEntity foundCustomer, CarEntity foundCar,
			OfficeEntity foundOfficeFrom, OfficeEntity foundOfficeTo) {
		LoanEntity loan = LoanEntity.builder().dateFrom(newLoan.getDateFrom())
				.dateTo(newLoan.getDateTo()).loanPrice(newLoan.getLoanPrice())
				.officeFrom(foundOfficeFrom).officeTo(foundOfficeTo).carLoanRelation(foundCar).custLoanRelation(foundCustomer)
				.build();
				
		return loan;
	}

	public static LoanTO toTO(LoanEntity savedLoan) {
		LoanTO loan = LoanTO.builder().dateFrom(savedLoan.getDateFrom())
				.dateTo(savedLoan.getDateTo()).loanPrice(savedLoan.getLoanPrice())
				.officeFromId(savedLoan.getOfficeFrom()== null ? null : savedLoan.getOfficeFrom().getId())
				.officeToId(savedLoan.getId())
				.customerId(savedLoan.getCarLoanRelation()== null ? null : savedLoan.getCarLoanRelation().getId())
				.carId(savedLoan.getCustLoanRelation()== null ? null : savedLoan.getCustLoanRelation().getId())
				.build();
		return loan;
	}

	
}

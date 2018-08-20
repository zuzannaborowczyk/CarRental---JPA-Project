package com.capgemini.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.capgemini.domain.OfficeEntity.OfficeEntityBuilder;

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
@Entity
public class LoanEntity {

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	
	@Column(nullable = false, length = 50)
	private Date dateFrom;
	@Column(nullable = false, length = 50)
	private Date dateTo;
	@Column(nullable = false, length = 50)
	private int loanPrice;
	@Version
	private long version;
	@ManyToOne
	OfficeEntity officeFrom;
	
	@ManyToOne
	OfficeEntity officeTo;
	@ManyToOne
	CarEntity carLoanRelation;
	@ManyToOne
	CustomerEntity custLoanRelation;
	
	public LoanEntity(Long id, Date dateFrom, Date dateTo, int loanPrice) {
	
		this.id = id;
		
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.loanPrice = loanPrice;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public int getLoanPrice() {
		return loanPrice;
	}
	public void setLoanPrice(int loanPrice) {
		this.loanPrice = loanPrice;
	}
	
	
	
}

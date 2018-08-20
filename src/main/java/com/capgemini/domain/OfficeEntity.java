package com.capgemini.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.capgemini.domain.CarEntity.CarEntityBuilder;

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
public class OfficeEntity {

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

	@Embedded
	@Column(nullable = false, length = 50)
	private AddressEntity address;
	@Column(nullable = false, length = 50)
	private String phoneNumber;
	@Version
	private long version;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "officeEntity")
	private List<EmployeeEntity> employees = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "officeFrom")
	private Set<LoanEntity> officeFrom = new HashSet<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "officeTo")
	private Set<LoanEntity> officeTo = new HashSet<>();

	public OfficeEntity(Long id, AddressEntity address, String phoneNumber, List<EmployeeEntity> employees) {

		this.id = id;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.employees = employees;
	}

	public List<EmployeeEntity> getAllEmployees() {
		return employees;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public void addEmployee(EmployeeEntity employeeToAdd) {
		this.employees.add(employeeToAdd);
		employeeToAdd.setOfficeEntity(this);

	}

	public void removeEmployee(EmployeeEntity employeeToRemove) {
		this.employees.remove(employeeToRemove);
		employeeToRemove.setOfficeEntity(null);
	}
}

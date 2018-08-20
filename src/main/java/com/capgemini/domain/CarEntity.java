package com.capgemini.domain;

import java.util.Date;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class CarEntity {
	
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
	private String carType;
	@Column(nullable = false, length = 50)
	private String carBrand;
	@Column(nullable = false, length = 50)
	private String color;
	@Column(nullable = false, length = 50)
	private int mileAge;
	@Column(nullable = false, length = 50)
	private int power;
	@Column(nullable = false, length = 50)
	private int engineCapacity;
	@Column(nullable = false, length = 50)
	private int productionYear;
	@Version
	private long version;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy="carLoanRelation")
	private Set<LoanEntity> carsRented = new HashSet<>();
	
	@ManyToMany
	private Set<EmployeeEntity> owners = new HashSet<>();
	
	public CarEntity(Long id, String carType, String carBrand, String color, int mileAge, int power, int engineCapacity,
			int productionYear) {
		
		this.id = id;
		this.carType = carType;
		this.carBrand = carBrand;
		this.color = color;
		this.mileAge = mileAge;
		this.power = power;
		this.engineCapacity = engineCapacity;
		this.productionYear = productionYear;
	}

	
	public Set<EmployeeEntity> getAllOwners() {
		return owners;
	}
	public void addOwner(EmployeeEntity newOwner) {
		getAllOwners().add(newOwner);
		newOwner.updateCarReference(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMileAge() {
		return mileAge;
	}

	public void setMileAge(int mileAge) {
		this.mileAge = mileAge;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(int engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	protected void updateOwnerReference(EmployeeEntity employeeEntity) {
		if(owners == null) {
			owners = new HashSet<>();
			owners.add(employeeEntity);
		}
		owners.add(employeeEntity);
	}
	

}

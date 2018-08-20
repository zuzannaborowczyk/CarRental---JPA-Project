package com.capgemini.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class EmployeeEntity {

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
	private String firstName;
	@Column(nullable = false, length = 50)
	private String lastName;
	//niepotrzebne
	@Column(nullable = false, length = 50)
	private long officeId;
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private EmployeePosition position;
	@Column(nullable = false, length = 50)
	private String birthdate;
	@Version
	private long version;
	@ManyToOne
	private OfficeEntity officeEntity = new OfficeEntity();
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="owners")
	private Set<CarEntity> cars = new HashSet<>();
	
	public EmployeeEntity(Long id, String firstName, String lastName, long officeId, EmployeePosition position, String birthdate,
			OfficeEntity officeEntity, Set<CarEntity> cars) {
	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.officeId = officeId;
		this.position = position;
		this.birthdate = birthdate;
		this.officeEntity = officeEntity;
		this.cars = cars;
	}
	
	
	
	
	//public void updateEmployeeReference(OfficeEntity officeEntity) {
	//	this.officeEntity = officeEntity;
	//}
	public Set<CarEntity> getAllCars() {
		return cars;
	}
	
	public void addCar(CarEntity newCar) {
		if (cars == null) {
			cars = new HashSet<>();
			cars.add(newCar);
			newCar.updateOwnerReference(this);
		}
		cars.add(newCar);
		newCar.updateOwnerReference(this);
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getOfficeId() {
		return officeId;
	}
	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}
	public EmployeePosition getPosition() {
		return position;
	}
	public void setPosition(EmployeePosition position) {
		this.position = position;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public OfficeEntity getOfficeEntity() {
		return officeEntity;
	}
	public void setOfficeEntity(OfficeEntity officeEntity) {
		this.officeEntity = officeEntity;
	}
	protected void updateCarReference(CarEntity carEntity) {
		cars.add(carEntity);
		
	}
	
	
}

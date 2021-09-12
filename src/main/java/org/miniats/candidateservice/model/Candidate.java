package org.miniats.candidateservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String uid;
	
	@NotNull
	@Column(name="company_uid")
	@Size(min=36, max=36, message="Company UID must be 36 characters long")
	private String companyUid;

	@NotNull
	@Column(name="first_name")
	@Size(max=128, message="First name must be max 128 characters long")
	private String firstName;

	@NotNull
	@Column(name="last_name")
	@Size(max=128, message="Last name must be max 128 characters long")
	private String lastName;

	@NotNull
	@Size(max=256, message="Email must be max 128 characters long")
	private String email;
	
	@Column(name="mobile_phone")
	@Size(max=32, message="Mobile phone number must be max 32 characters long")
	private String mobilePhone;
	
	@Column(name="current_salary")
	@Min(value=0, message="Current salary must be equals to or greater than one")
	private Long currentSalary;
	
	@Column(name="desired_salary")
	@Min(value=0, message="Desired salary must be equals to or greater than one")
	private Long desiredSalary;
	
	@Column(nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean deleted = false;
}

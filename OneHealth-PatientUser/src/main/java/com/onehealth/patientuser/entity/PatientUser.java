package com.onehealth.patientuser.entity;

import jakarta.persistence.*;

@Entity
//@Table(name = "user_registration")
public class PatientUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "mobile_number", length = 50)
	private String mobileNumber;

	@Column(name = "emailid", length = 100, nullable = false)
	private String emailId;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "password", length = 30)
	private String password;

	public PatientUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientUser(Long userId, String mobileNumber, String emailId, String firstName, String lastName,
			String password) {
		super();
		this.userId = userId;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "PatientUser [userId=" + userId + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

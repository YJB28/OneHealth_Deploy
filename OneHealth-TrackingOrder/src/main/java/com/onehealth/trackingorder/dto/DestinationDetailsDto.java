package com.onehealth.trackingorder.dto;

import java.util.Date;

public class DestinationDetailsDto {

	private Long patientId;
	private Long userId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String address;
	private Long pinCode;
	private String country;
	private String city;
	private String gender;
	private Integer age;
	private Date dob;
	private String bloodGroup;
	private Double height;
	private Double weight;
	private String maritalStatus;
	private String emailId;

	public DestinationDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DestinationDetailsDto(Long patientId, Long userId, String firstName, String lastName, String mobileNumber,
			String address, Long pinCode, String country, String city, String gender, Integer age, Date dob,
			String bloodGroup, Double height, Double weight, String maritalStatus, String emailId) {
		super();
		this.patientId = patientId;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.pinCode = pinCode;
		this.country = country;
		this.city = city;
		this.gender = gender;
		this.age = age;
		this.dob = dob;
		this.bloodGroup = bloodGroup;
		this.height = height;
		this.weight = weight;
		this.maritalStatus = maritalStatus;
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "DestinationDetailsDto [patientId=" + patientId + ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobileNumber=" + mobileNumber + ", address=" + address + ", pinCode="
				+ pinCode + ", country=" + country + ", city=" + city + ", gender=" + gender + ", age=" + age + ", dob="
				+ dob + ", bloodGroup=" + bloodGroup + ", height=" + height + ", weight=" + weight + ", maritalStatus="
				+ maritalStatus + ", emailId=" + emailId + "]";
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
